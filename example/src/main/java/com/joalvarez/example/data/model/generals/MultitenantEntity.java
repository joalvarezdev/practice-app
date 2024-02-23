package com.joalvarez.example.data.model.generals;

import java.util.UUID;

public abstract class MultitenantEntity {

	public abstract UUID getUserId();
	public abstract void setUserId(UUID userId);
}
