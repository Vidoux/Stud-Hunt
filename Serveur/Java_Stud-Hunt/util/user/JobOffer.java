package util.user;

public class JobOffer {
	private int offerType;

	public JobOffer(int offerType) {
		this.setOfferType(offerType);
	}

	public int getOfferType() {
		return offerType;
	}

	public void setOfferType(int offerType) {
		this.offerType = offerType;
	}

}
