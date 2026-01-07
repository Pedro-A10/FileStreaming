package service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import util.RangeUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

@Service
public class VideoStreamService {

    private static final String VIDEO_PATH = "src/main/resources/video/video.mp4";

    public ResponseEntity<byte[]> streamVideo (String range) throws IOException {
        File video = new File(VIDEO_PATH);
        long fileSize = video.length();

        RangeUtils.Range rangeInfo = RangeUtils.parseRange(range, fileSize);

        try(RandomAccessFile raf = new RandomAccessFile(video, "r")) {
            raf.seek(rangeInfo.start);

            byte[] data = new byte[(int) rangeInfo.length];
            raf.readFully(data);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "video/mp4");
            headers.add("Accept-Ranges", "bytes");
            headers.add("Content-Length", String.valueOf(rangeInfo.length));
            headers.add("Content-Range", "bytes " + rangeInfo.start + "-" + rangeInfo.end + "/" + fileSize);

            return ResponseEntity
                    .status(HttpStatus.PARTIAL_CONTENT)
                    .headers(headers)
                    .body(data);
        }
    }
}
