# File Streamer

Um projeto simples para streaming de vídeo com suporte a requisições parciais (range requests), permitindo reprodução eficiente no navegador.

# Features

- Video streaming over HTTP.
- Supports seeking and partial playback using the Range header.
- Simple front-end integration (index.html) to test streaming.

# Modular code:

- VideoController: handles HTTP requests.
- VideoStreamService: manages reading and sending video data.
- RangeUtils: parses and validates the Range header.

# Technologies

- Java 17+ (or compatible version with HTTP server framework)
- HTTP server framework (e.g., Spring Boot, SparkJava, etc.)
- HTML5 <video> for browser playback
