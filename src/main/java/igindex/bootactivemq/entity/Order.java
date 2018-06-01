package igindex.bootactivemq.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
	String account;
	String submittedAt;
	String receivedAt;
	String market;
	String action;
	String size;
	
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * @return the submittedAt
	 */
	public String getSubmittedAt() {
		return submittedAt;
	}
	/**
	 * @param submittedAt the submittedAt to set
	 */
	public void setSubmittedAt(String submittedAt) {
		this.submittedAt = submittedAt;
	}
	/**
	 * @return the receivedAt
	 */
	public String getReceivedAt() {
		return receivedAt;
	}
	/**
	 * @param receivedAt the receivedAt to set
	 */
	public void setReceivedAt(String receivedAt) {
		this.receivedAt = receivedAt;
	}
	/**
	 * @return the market
	 */
	public String getMarket() {
		return market;
	}
	/**
	 * @param market the market to set
	 */
	public void setMarket(String market) {
		this.market = market;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Order [account=" + account + ", submittedAt=" + submittedAt + ", receivedAt=" + receivedAt + ", market="
				+ market + ", action=" + action + ", size=" + size + "]";
	}	
	
}
