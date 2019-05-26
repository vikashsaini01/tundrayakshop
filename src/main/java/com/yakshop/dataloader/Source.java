package com.yakshop.dataloader;

public interface Source {

	class DefaultXmlSource implements Source {
		private String xmlFilePath;

		public DefaultXmlSource(String xmlFilePath) {
			if (xmlFilePath == null || xmlFilePath.isEmpty()) {
				throw new IllegalArgumentException("File path is null or empty.");
			}
			this.xmlFilePath = xmlFilePath;
		}

		public String getXmlFilePath() {
			return this.xmlFilePath;
		}
	}
}