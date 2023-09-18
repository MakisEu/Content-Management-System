package api;

/**
 * Base class every type of content inherits
 */
public class Content{
	protected static int nextID=0;
	String Title,user,ID;
	Body Body;
	int charLimit,votes=0;


	/**
	 * @param Title
	 * @param BodyPost
	 * @param charLimit
	 * Simple constructor for creating a piece of content (Can only be used from same class)
	 */
	Content(String Title, Body BodyPost, String user, int charLimit){
		this.Title=Title;
		this.Body = (Body) BodyPost;
		this.user=user;
		this.charLimit=charLimit;
		this.ID="object#"+user+"#"+String.valueOf(nextID);
		nextID++;

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
		return Body;
	}

	/**
	 * @param title
	 * Set the Title of the content
	 */
	public void setTitle(String title) {
		Title = title;
	}

	/**
	 * @param body
	 * Set the body of the text
	 */
	public void setBody(Body body) {
		this.Body = (Body)body;
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

	/**
	 * @return The number of votes/likes
	 */
	public int getVotes() {
		return votes;
	}

	/**
	 * @return the unique ID of the content
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @return the name of the user that created the content
	 */
	public String getUser() {
		return user;
	}

	/**
	 *
	 * @param 	Text The text of the content
	 * @return 	the text, if text<=charLimit else returns the text but only with charLimit characters
	 */

	public String Check_char_count(String Text){
		if (Text.length()>charLimit){
			return Text.substring(0,charLimit-1);
		}
		return Text;
	}
}
