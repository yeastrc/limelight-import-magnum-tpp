/*
 * Original author: Michael Riffle <mriffle .at. uw.edu>
 *                  
 * Copyright 2018 University of Washington - Seattle, WA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.yeastrc.limelight.xml.magnumtpp.objects;

import java.io.File;

public class ConversionParameters {
		
	/**
	 * @return the fastaFile
	 */
	public File getFastaFile() {
		return fastaFile;
	}
	/**
	 * @param fastaFile the fastaFile to set
	 */
	public void setFastaFile(File fastaFile) {
		this.fastaFile = fastaFile;
	}
	/**
	 * @return the magnumConfFile
	 */
	public File getMagnumConfFile() {
		return magnumConfFile;
	}
	/**
	 * @param magnumConfFile the magnumConfFile to set
	 */
	public void setMagnumConfFile(File magnumConfFile) {
		this.magnumConfFile = magnumConfFile;
	}
	/**
	 * @return the limelightXMLOutputFile
	 */
	public File getLimelightXMLOutputFile() {
		return limelightXMLOutputFile;
	}
	/**
	 * @param limelightXMLOutputFile the limelightXMLOutputFile to set
	 */
	public void setLimelightXMLOutputFile(File limelightXMLOutputFile) {
		this.limelightXMLOutputFile = limelightXMLOutputFile;
	}
	/**
	 * @return the pepXMLFile
	 */
	public File getPepXMLFile() {
		return pepXMLFile;
	}
	/**
	 * @param pepXMLFile the pepXMLFile to set
	 */
	public void setPepXMLFile(File pepXMLFile) {
		this.pepXMLFile = pepXMLFile;
	}
	/**
	 * @return the conversionProgramInfo
	 */
	public ConversionProgramInfo getConversionProgramInfo() {
		return conversionProgramInfo;
	}
	/**
	 * @param conversionProgramInfo the conversionProgramInfo to set
	 */
	public void setConversionProgramInfo(ConversionProgramInfo conversionProgramInfo) {
		this.conversionProgramInfo = conversionProgramInfo;
	}

	public Boolean getImportDecoys() {
		return importDecoys;
	}

	public void setImportDecoys(Boolean importDecoys) {
		this.importDecoys = importDecoys;
	}

	public String getIndependentDecoyPrefix() {
		return independentDecoyPrefix;
	}

	public void setIndependentDecoyPrefix(String independentDecoyPrefix) {
		this.independentDecoyPrefix = independentDecoyPrefix;
	}

	private File fastaFile;
	private File magnumConfFile;
	private File limelightXMLOutputFile;
	private File pepXMLFile;
	private ConversionProgramInfo conversionProgramInfo;
	private boolean importDecoys = false;
	private String independentDecoyPrefix;
	
}
