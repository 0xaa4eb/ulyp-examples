package org.example.aeron;

import io.aeron.Aeron;
import io.aeron.Subscription;
import io.aeron.driver.MediaDriver;
import io.aeron.driver.ThreadingMode;
import io.aeron.logbuffer.FragmentHandler;

public class AeronSubscriber {

    public static void main(String[] args) {
        MediaDriver.Context context = new MediaDriver.Context();
        context.threadingMode(ThreadingMode.SHARED);

        try (MediaDriver mediaDriver = MediaDriver.launchEmbedded(context)) {

            Aeron.Context ctx = new Aeron.Context();
            ctx.aeronDirectoryName(mediaDriver.aeronDirectoryName());

            Aeron aeron = Aeron.connect(ctx);

            Subscription subscription = aeron.addSubscription("aeron:udp?endpoint=localhost:20121", 1001);
            FragmentHandler fragmentHandler = (buffer, offset, length, header) -> {
                String data = buffer.getStringWithoutLengthUtf8(offset, length);
                System.out.printf("Message from session %d (%d@%d) <<%s>>%n", header.sessionId(), length, offset, data);
            };

            while (!Thread.currentThread().isInterrupted()) {
                int fragmentsRead = subscription.poll(fragmentHandler, 10);
                if (fragmentsRead == 0) {
                    Thread.sleep(10);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
