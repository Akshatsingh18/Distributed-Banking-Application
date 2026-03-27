$ErrorActionPreference = "Stop"

function Get-JavaMajorVersion {
    $versionOutput = & cmd /c "java -version 2>&1"
    if ($LASTEXITCODE -ne 0) {
        throw "Java is not installed or not available on PATH."
    }

    $firstLine = $versionOutput | Select-Object -First 1
    if ($firstLine -match '"(?<version>\d+(?:\.\d+)?)') {
        $parsedVersion = $matches.version
        if ($parsedVersion.StartsWith("1.")) {
            return [int]($parsedVersion.Split(".")[1])
        }
        return [int]($parsedVersion.Split(".")[0])
    }

    throw "Unable to determine Java version."
}

$javaMajorVersion = Get-JavaMajorVersion
if ($javaMajorVersion -lt 17) {
    throw "Java 17 or newer is required. Current version: $javaMajorVersion. Install JDK 17+, or run the app with Docker using 'docker build -t bank-app . && docker run -p 8080:8080 bank-app'."
}

$artifact = $null

$jarArtifact = Get-ChildItem ".\target\*.jar" -ErrorAction SilentlyContinue |
    Where-Object { $_.Name -notlike "*.original" } |
    Sort-Object LastWriteTime -Descending |
    Select-Object -First 1

if ($jarArtifact) {
    $artifact = $jarArtifact.FullName
}

if (-not $artifact) {
    Write-Host "No built JAR found. Building the project first..."
    & .\mvnw.cmd -DskipTests package
    if ($LASTEXITCODE -ne 0) {
        throw "Build failed."
    }

    $jarArtifact = Get-ChildItem ".\target\*.jar" -ErrorAction SilentlyContinue |
        Where-Object { $_.Name -notlike "*.original" } |
        Sort-Object LastWriteTime -Descending |
        Select-Object -First 1

    if (-not $jarArtifact) {
        throw "Build completed, but no runnable JAR was found in .\target."
    }

    $artifact = $jarArtifact.FullName
}

Write-Host "Starting application from $artifact"
& java -jar $artifact
