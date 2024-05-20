package com.ross.gamis.controller.api.dto.game.in;

public class GameDtoIn {
    private long id;
    private String title;
    private String description;
    private long developerId;

    public GameDtoIn() {
    }

	public GameDtoIn(String title, String description, long developerId) {
		this.title = title;
		this.description = description;
		this.developerId = developerId;
	}

	public GameDtoIn(long id, String title, String description, long developerId) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.developerId = developerId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(long developerId) {
		this.developerId = developerId;
	}

    @Override
	public String toString() {
		return "GameDtoPost [id=" + id + ", title=" + title + ", description=" + description + ", developerId="
				+ developerId + "]";
	}
}
