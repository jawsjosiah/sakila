package vo;

public class SalesByStore {
	private String store;
	private String manager;
	private int total_sales;
	
	@Override
	public String toString() {
		return "SalesByStore [store=" + store + ", manager=" + manager + ", total_sales=" + total_sales + "]";
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public int getTotal_sales() {
		return total_sales;
	}

	public void setTotal_sales(int total_sales) {
		this.total_sales = total_sales;
	}
	
}
