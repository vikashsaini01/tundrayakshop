package com.yakshop.dataloader;

import java.util.List;

import com.yakshop.model.Yak;

public interface YakLoader {
	
	public List<Yak> loadYaks(Source source);

}
