// Simple test program for service provider framework
package org.effectivejava.examples.chapter02.item01;

public class Test {
	public static void main(String[] args) {
		// Providers would execute these lines
		Services.registerDefaultProvider(DEFAULT_PROVIDER);
		Services.registerProvider("comp", COMP_PROVIDER);
		Services.registerProvider("armed", ARMED_PROVIDER);

		// Clients would execute these lines
		// The newInstance method is the static factory method
		Service s1 = Services.newInstance();
		// Just a string is given to uniquely identify a type and that type is returned.
		// This unique string can be any data that can be used to distinguish a type uniquely
		Service s2 = Services.newInstance("comp");
		Service s3 = Services.newInstance("armed");
		System.out.printf("%s, %s, %s%n", s1, s2, s3);
	}

	private static Provider DEFAULT_PROVIDER = new Provider() {
		public Service newService() {
			return new Service() {
				@Override
				public String toString() {
					return "Default service";
				}
			};
		}
	};

	private static Provider COMP_PROVIDER = new Provider() {
		public Service newService() {
			return new Service() {
				@Override
				public String toString() {
					return "Complementary service";
				}
			};
		}
	};

	private static Provider ARMED_PROVIDER = new Provider() {
		public Service newService() {
			return new Service() {
				@Override
				public String toString() {
					return "Armed service";
				}
			};
		}
	};
}
