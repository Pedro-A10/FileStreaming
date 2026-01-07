package util;

public class RangeUtils {

    public static class Range {

        public long start;
        public long end;
        public long length;

        public Range(long start, long end) {
            this.start = start;
            this.end = end;
            this.length = end - start +1;
        }
    }

    public static Range parseRange(String rangeHeader, long fileSize) {
        if (rangeHeader == null || !rangeHeader.startsWith("bytes=")){
            return new Range(0, fileSize-1);
        }

        String[] ranges = rangeHeader.replace("bytes=", "").split("-");
        long start = Long.parseLong(ranges[0]);
        long end = ranges.length > 1 && !ranges[1].isEmpty()
                ? Long.parseLong(ranges[1])
                : fileSize - 1;

        return new Range(start, end);
    }
}
