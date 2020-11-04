package model;

enum Mirror{
	
	RIGHT_MIRROR("/"),
	LEFT_MIRROR("\\"),
	EMPTY(" ");
	
	private String mirror;
	
	private Mirror(String mirror) {
		this.mirror = mirror;
	}

	public String getString() {
		return mirror;
	}
	
	
}