package in.ineuron.dto;

public class BookDto {
	private Integer bookId;
	private String bookName;
	private String bookTitle;
	private String bookAuthor;
	private String bookCategory;
	private Integer bookQuantity;
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}
	public Integer getBookQuantity() {
		return bookQuantity;
	}
	public void setBookQuantity(Integer bookQuantity) {
		this.bookQuantity = bookQuantity;
	}
	@Override
	public String toString() {
		return "BookDto [bookId=" + bookId + ", bookName=" + bookName + ", bookTitle=" + bookTitle + ", bookAuthor="
				+ bookAuthor + ", bookCategory=" + bookCategory + ", bookQuantity=" + bookQuantity + "]";
	}
	
	
}
