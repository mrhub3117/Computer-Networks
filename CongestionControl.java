import java.util.Random;

public class CongestionControl {
    public static void main(String[] args) {
        int cwnd = 1; 
        int ssthresh = 16; 
        int maxPackets = 50; 
        Random random = new Random();

        System.out.println("Starting TCP Tahoe Congestion Control Simulation...");
        System.out.println("Initial cwnd: " + cwnd + ", ssthresh: " + ssthresh);

        for (int packetsSent = 1; packetsSent <= maxPackets; packetsSent++) {
            System.out.println("Packet " + packetsSent + " | cwnd: " + cwnd);

            if (random.nextInt(10) < 2) { 
                System.out.println("Packet loss detected! Switching to fast retransmit...");
                ssthresh = cwnd / 2;
                cwnd = 1; // Reset congestion window to 1
                System.out.println("New cwnd: " + cwnd + ", ssthresh: " + ssthresh);
                continue;
            }

            
            if (cwnd < ssthresh) {
                cwnd *= 2; 
            } else { 
                cwnd += 1; 
            }

            
            cwnd = Math.min(cwnd, maxPackets);
        }

        System.out.println("Simulation Complete!");
    }
}
