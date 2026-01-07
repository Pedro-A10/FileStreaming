package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.VideoStreamService;

import java.io.IOException;

@RestController
@RequestMapping("/video")
public class VideoController {

    private final VideoStreamService videoStreamService;

    public VideoController(VideoStreamService videoStreamService) {
        this.videoStreamService = videoStreamService;
    }

    @GetMapping
    public ResponseEntity<byte[]> streamVideo(
            @RequestHeader(value = "Range", required = false) String range) throws IOException {

        return videoStreamService.streamVideo(range);
    }
}