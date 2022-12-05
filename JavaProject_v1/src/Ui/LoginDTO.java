package Ui;

import java.sql.Timestamp;

public class LoginDTO{
	private String user_name;
	private String user_email;
	private String user_id;
	private String user_pw;
	private String consti;
	private String cardigan;
	private String padding;
	private String windbreaker;
	private String jacket;
	private String zipup;
	private String coat;
	private String yaasang;
	private String fleece;
	private String shortsleeve;
	private String blouse;
	private String shirts;
	private String sleeveless;
	private String knit;
	private String vest;
	private String longsleeve;
	private String hoodie;
	private String mtm;
	private String longpants;
	private String slacks;
	private String jeans;
	private String shortpants;
	private String miniskirt;
	private String longskirt;
	private String muffler;
	private String longdress;
	private String shortdress;
	
	public LoginDTO() {}
	
	public LoginDTO(String user_name, String user_email, String user_id, String user_pw, String consti, String cardigan, String padding, String windbreaker, 
			String jacket, String zipup, String coat, String yaasang, String fleece, String shortsleeve,String blouse, String shirts , String sleeveless, String knit, 
			String vest, String longsleeve, String hoodie, String mtm, String longpants,String slacks, String jeans, String shortpants, String miniskirt, 
			String longskirt, String muffler, String longdress, String shortdress) {
		super();
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.consti = consti;
		this.cardigan = cardigan;
		this.padding = padding;
		this.windbreaker = windbreaker;
		this.jacket = jacket;
		this.zipup = zipup;
		this.coat = coat;
		this.yaasang = yaasang;
		this.fleece = fleece;
		this.shortsleeve = shortsleeve;
		this.blouse = blouse;
		this.shirts = shirts;
		this.sleeveless = sleeveless;
		this.knit = knit;
		this.vest = vest;
		this.longsleeve = longsleeve;
		this.hoodie = hoodie;
		this.mtm = mtm;
		this.longpants = longpants;
		this.slacks = slacks;
		this.jeans = jeans;
		this.shortpants = shortpants;
		this.miniskirt = miniskirt;
		this.longskirt = longskirt;
		this.muffler = muffler;
		this.longdress = longdress;
		this.shortdress = shortdress;
		
	}
	
	public String getId() {
		return user_id;
	}
	
	public void setId(String user_id) {
		this.user_id = user_id;
	}
	
	public String getPw() {
		return user_pw;
	}
	
	public void setPw(String user_pw) {
		this.user_pw = user_pw;
	}
	
	public String getName() {
		return user_name;
	}
	
	public void setName(String user_name) {
		this.user_name = user_name;
	}
	
	public String getEmail() {
		return user_email;
	}
	
	public void setEmail(String user_email) {
		this.user_email = user_email;
	}
	
	public String getConsti() {
		return consti;
	}
	
	public void setConsti(String consti) {
		this.consti = consti;
	}
	
	public String getCardiigan() {
		return cardigan;
	}
	
	public void setCardigan(String cardigan) {
		this.cardigan = cardigan;
	}
	
	public String getPadding() {
		return padding;
	}
	
	public void setPadding(String padidng) {
		this.padding = padidng;
	}
	
	public String getWindbreaker() {
		return windbreaker;
	}
	
	public void setWindbreaker(String windbreaker) {
		this.windbreaker = windbreaker;
	}
	
	public String getJacket() {
		return jacket;
	}
	
	public void setJacket(String jacket) {
		this.jacket = jacket;
	}
	
	public String getZipup() {
		return zipup;
	}
	
	public void setZipup(String zipup) {
		this.zipup = zipup;
	}
	
	public String getCoat() {
		return coat;
	}
	
	public void setCoat(String coat) {
		this.coat = coat;
	}
	
	public String getYaasang() {
		return yaasang;
	}
	
	public void setYaasang(String yaasang) {
		this.yaasang = yaasang;
	}
	
	public String getFleece() {
		return fleece;
	}
	
	public void setFleece(String fleece) {
		this.fleece = fleece;
	}
	
	public String getShortsleeve() {
		return shortsleeve;
	}
	
	public void setShortsleeve(String shortsleeve) {
		this.shortsleeve = shortsleeve;
	}
	
	public String getBlouse() {
		return blouse;
	}
	
	public void setBlouse(String blouse) {
		this.blouse = blouse;
	}
	
	public String getShirts() {
		return shirts;
	}
	
	public void setShirts(String shirts) {
		this.shirts = shirts;
	}
	
	public String getSleeveless() {
		return sleeveless;
	}
	
	public void setSleeveless(String sleeveless) {
		this.sleeveless = sleeveless;
	}
	
	public String getKnit() {
		return knit;
	}
	
	public void setKnit(String knit) {
		this.knit = knit;
	}
	
	public String getVest() {
		return vest;
	}
	
	public void setVest(String vest) {
		this.vest = vest;
	}
	
	public String getLongsleeves() {
		return longsleeve;
	}
	
	public void setLongsleeves(String longsleeve) {
		this.longsleeve = longsleeve;
	}
	
	public String getHoodie() {
		return hoodie;
	}
	
	public void setHoodie(String hoodie) {
		this.hoodie = hoodie;
	}
	
	public String getMtm() {
		return mtm;
	}
	
	public void setMtm(String mtm) {
		this.mtm = mtm;
	}
	
	public String getLongpants() {
		return longpants;
	}
	
	public void setLongpants(String longpants) {
		this.longpants = longpants;
	}
	
	public String getSlacks() {
		return slacks;
	}
	
	public void setSlacks(String slacks) {
		this.slacks = slacks;
	}
	
	public String getJeans() {
		return jeans;
	}
	
	public void setJeans(String jeans) {
		this.jeans = jeans;
	}
	
	public String getShortpants() {
		return shortpants;
	}
	
	public void setShortpants(String shortpants) {
		this.shortpants = shortpants;
	}
	
	public String getMiniskirt() {
		return miniskirt;
	}
	
	public void setMiniskirt(String miniskirt) {
		this.miniskirt = miniskirt;
	}
	
	public String getLongskirt() {
		return longskirt;
	}
	
	public void setLongskirt(String longskirt) {
		this.longskirt = longskirt;
	}
	
	public String getMuffler() {
		return muffler;
	}
	
	public void setMuffler(String muffler) {
		this.muffler = muffler;
	}
	
	public String getLongdress() {
		return longdress;
	}
	
	public void setLongdress(String longdress) {
		this.longdress = longdress;
	}
	
	public String getShortdress() {
		return shortdress;
	}
	
	public void setShortdress(String shortdress) {
		this.shortdress = shortdress;
	}
	
}