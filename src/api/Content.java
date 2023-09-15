package api;

/**
 * Base class every type of content inherits
 */
public class Content{
	String Title;
	Body BodyPost;
	int charLimit;
	int votes=0;

	/**
	 * @param Title
	 * @param BodyPost
	 * @param charLimit
	 * Simple constructor for creating a piece of content
	 */
	public Content(String Title, Body BodyPost, int charLimit){
		this.Title=Title;
		this.BodyPost = (Body) BodyPost;
		this.charLimit=charLimit;
	}

	/**
	 * @return the limit of characters for the body of the content
	 * */
	public int getCharLimit() {
		return charLimit;
	}

	/**
	 * @return the title of the content
	 */
	public String getTitle() {
		return Title;
	}

	/**
	 * @return the body of the content
	 */
	public Body getBody() {
		return BodyPost;
	}

	/**
	 * @param title
	 * Set the Title of the content
	 */
	public void setTitle(String title) {
		Title = title;
	}

	/**
	 * @param bodyPost
	 * Set the body of the text
	 */
	public void setBody(BodyPost bodyPost) {
		BodyPost = bodyPost;
	}

	/**
	 * Increases the like counter of this post
	 */
	public void Like(){
		votes++;
	}
	/**
	 * Decreases the like counter of this post
	 */
	public void Dislike(){
		votes--;
	}
}