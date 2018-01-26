package benchmark;

import java.net.UnknownServiceException;

public class SortMain {

    public static void main(String... args) throws UnsortedException {
        throw new UnsortedException("Exception Message");
    }

}
