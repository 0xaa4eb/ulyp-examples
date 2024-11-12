package org.example.aeron;

import io.aeron.Aeron;
import io.aeron.ConcurrentPublication;
import io.aeron.driver.MediaDriver;
import io.aeron.driver.ThreadingMode;
import org.agrona.BufferUtil;
import org.agrona.concurrent.UnsafeBuffer;

import java.util.concurrent.TimeUnit;

public class AeronPublisher {

    public static void main(String[] args) {
        MediaDriver.Context context = new MediaDriver.Context();
        context.threadingMode(ThreadingMode.SHARED);

        try (MediaDriver mediaDriver = MediaDriver.launchEmbedded(context)) {

            Aeron.Context ctx = new Aeron.Context();
            ctx.aeronDirectoryName(mediaDriver.aeronDirectoryName());

            Aeron aeron = Aeron.connect(ctx);

            ConcurrentPublication publication = aeron.addPublication("aeron:udp?endpoint=localhost:20121", 1001);
            while (!publication.isConnected()) {
                TimeUnit.MILLISECONDS.sleep(100);
            }

            UnsafeBuffer buffer = new UnsafeBuffer(BufferUtil.allocateDirectAligned(256, 64));
            int index = 0;

            while (!Thread.currentThread().isInterrupted()) {
                int len = buffer.putStringWithoutLengthUtf8(0, "Message" + index);
                publication.offer(buffer, 0, len);
                index++;
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
