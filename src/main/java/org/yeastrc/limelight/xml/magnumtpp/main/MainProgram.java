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

package org.yeastrc.limelight.xml.magnumtpp.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.yeastrc.limelight.xml.magnumtpp.constants.Constants;
import org.yeastrc.limelight.xml.magnumtpp.objects.ConversionParameters;
import org.yeastrc.limelight.xml.magnumtpp.objects.ConversionProgramInfo;

import picocli.CommandLine;

@CommandLine.Command(name = "java -jar " + Constants.CONVERSION_PROGRAM_NAME,
		mixinStandardHelpOptions = true,
		version = Constants.CONVERSION_PROGRAM_NAME + " " + Constants.CONVERSION_PROGRAM_VERSION,
		sortOptions = false,
		synopsisHeading = "%n",
		descriptionHeading = "%n@|bold,underline Description:|@%n%n",
		optionListHeading = "%n@|bold,underline Options:|@%n",
		description = "Convert the results of a Magnum + TPP analysis to a Limelight XML file suitable for import into Limelight.\n\n" +
				"More info at: " + Constants.CONVERSION_PROGRAM_URI
)
public class MainProgram implements Runnable {

	@CommandLine.Option(names = { "-m", "--magnum-conf" }, required = true, description = "Path to Magnum .conf file")
	private File magnumFile;

	@CommandLine.Option(names = { "-p", "--pepxml" }, required = true, description = "Full path to pepXML file output by Magnum")
	private File pepXMLFile;

	@CommandLine.Option(names = { "-f", "--fasta-file" }, required = true, description = "Full path to FASTA file used in the experiment.")
	private File fastaFile;

	@CommandLine.Option(names = { "-o", "--out-file" }, required = true, description = "Full path to use for the Limelight XML output file (including file name).")
	private File outFile;

	@CommandLine.Option(names = { "-d", "--import-decoys" }, required = false, description = "If present, decoys will be included in the Limelight XML output.")
	private boolean importDecoys = false;

	@CommandLine.Option(names = { "-i", "--independent-decoy-prefix" }, required = false, description = "If present, any hits to proteins that begin with this string will be considered \"independent decoys,\" for the purpose of FDR estimation. See: https://pubmed.ncbi.nlm.nih.gov/21876204/")
	private String independentDecoyPrefix;

	@CommandLine.Option(names = { "-v", "--verbose" }, required = false, description = "If this parameters is present, error messages will include a full stacktrace.")
	private boolean verboseRequested = false;

	private String[] args;

	public void run() {

		printRuntimeInfo();

		if( !magnumFile.exists() ) {
			System.err.println( "Could not find magnum conf file: " + magnumFile.getAbsolutePath() );
			System.exit( 1 );
		}

		if( !pepXMLFile.exists() ) {
			System.err.println( "Could not find pepXML file: " + pepXMLFile.getAbsolutePath() );
			System.exit( 1 );
		}

		if( !fastaFile.exists() ) {
			System.err.println( "Could not find fasta file: " + fastaFile.getAbsolutePath() );
			System.exit( 1 );
		}

		ConversionProgramInfo cpi = ConversionProgramInfo.createInstance( String.join( " ",  args ) );

		ConversionParameters cp = new ConversionParameters();
		cp.setConversionProgramInfo( cpi );
		cp.setFastaFile( fastaFile );
		cp.setMagnumConfFile( magnumFile );
		cp.setPepXMLFile( pepXMLFile );
		cp.setLimelightXMLOutputFile( outFile );
		cp.setImportDecoys(importDecoys);
		cp.setIndependentDecoyPrefix(independentDecoyPrefix);

		try {
			ConverterRunner.createInstance().convertMagnumTPPToLimelightXML(cp);

		} catch(Throwable t) {

			System.err.println(t.getMessage());

			if(verboseRequested) {
				t.printStackTrace();
			}

			System.exit(1);

		}

		System.exit( 0 );
	}

	public static void main( String[] args ) {

		MainProgram mp = new MainProgram();
		mp.args = args;

		CommandLine.run(mp, args);
	}

	/**
	 * Print runtime info to STD ERR
	 * @throws Exception
	 */
	public void printRuntimeInfo() {

		try( BufferedReader br = new BufferedReader( new InputStreamReader( MainProgram.class.getResourceAsStream( "run.txt" ) ) ) ) {

			String line = null;
			while ( ( line = br.readLine() ) != null ) {

				line = line.replace( "{{URL}}", Constants.CONVERSION_PROGRAM_URI );
				line = line.replace( "{{VERSION}}", Constants.CONVERSION_PROGRAM_VERSION );

				System.err.println( line );

			}

			System.err.println( "" );

		} catch ( Exception e ) {
			System.out.println( "Error printing runtime information." );
		}
	}

}
