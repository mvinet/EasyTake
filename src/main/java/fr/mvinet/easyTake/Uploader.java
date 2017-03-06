package fr.mvinet.easyTake;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicHeader;

import apache.Header;

/**
 * @author mvinet
 */
public class Uploader {

	/**
	 * Name
	 */
	private String name;
	
	/**
	 * Host
	 */
	private String host;
	
	/**
	 * Path for the upload of image
	 */
	private String pathUpload;
	
	/**
	 * List of header
	 */
	private List<BasicHeader> header;
	
	/**
	 * Constructor
	 */
	public Uploader(String name) {
		this.name = name;
		this.header = new ArrayList();
	}

	
	
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the pathUpload
	 */
	public String getPathUpload() {
		return pathUpload;
	}

	/**
	 * @param pathUpload the pathUpload to set
	 */
	public void setPathUpload(String pathUpload) {
		this.pathUpload = pathUpload;
	}

	/**
	 * @return the header
	 */
	public List<BasicHeader> getHeader() {
		return header;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
