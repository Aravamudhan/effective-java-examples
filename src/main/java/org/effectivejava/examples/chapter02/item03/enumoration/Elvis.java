// Enum singleton - the preferred approach - page 18
package org.effectivejava.examples.chapter02.item03.enumoration;

public enum Elvis {
	INSTANCE;

	public void leaveTheBuilding() {
		System.out.println("Whoa baby, I'm outta here!");
	}

	// This code would normally appear outside the class!
	public static void main(String[] args) throws Exception {
		Elvis elvis = Elvis.INSTANCE;
		elvis.leaveTheBuilding();
		TicketCounter ticketCounter = TicketCounter.INSTANCE;
		ticketCounter.giveTicket();
		ticketCounter.giveTicket();
		ticketCounter.giveTicket();
		ticketCounter.giveTicket();
		Thread t = new Thread("Subthread") {
			public void run() {
				System.out.println("------" + Thread.currentThread().getName() + " is started");
				TicketCounter ticketCounter = TicketCounter.INSTANCE;
				ticketCounter.giveTicket();
				ticketCounter.giveTicket();
				ticketCounter.giveTicket();
				ticketCounter.giveTicket();
				System.out.println("------" + Thread.currentThread().getName() + " is completed");
			}
		};
		t.start();
		Thread.sleep(100);
		ticketCounter.giveTicket();
		ticketCounter.giveTicket();
		ticketCounter.giveTicket();
		ticketCounter.giveTicket();
		ticketCounter.giveTicket();
		ticketCounter.giveTicket();
		ticketCounter.giveTicket();
		ticketCounter.giveTicket();
		ticketCounter.giveTicket();
		System.out.println("------" + Thread.currentThread().getName() + " is completed");
	}
}

// A singleton implemented using enum. Singletons are useful one and only when
// the count of a resource can not be more than one. When we have to keep using
// only one resource through out the app. For example, a hardware resource in an
// embedded system.
enum TicketCounter {
	INSTANCE(10);

	private int ticketCount;

	// Only private constructors are allowed
	private TicketCounter(int ticketCount) {
		this.ticketCount = ticketCount;
	}

	public void giveTicket() {
		if (ticketCount > 0) {
			System.out.println("Ticket is being given");
			System.out.println("Remaining tickets " + ticketCount);
			ticketCount--;
		} else {
			System.out.println("The seats are full");
		}
	}
}