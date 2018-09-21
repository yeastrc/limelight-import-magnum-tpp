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

import jargs.gnu.CmdLineParser;
import jargs.gnu.CmdLineParser.IllegalOptionValueException;
import jargs.gnu.CmdLineParser.UnknownOptionException;

/**
 * @author Michael Riffle
 * @date Feb 21, 2018
 *
 */
public class MainProgram {

	public static void main( String[] args ) throws Throwable {

		printRuntimeInfo();

		if( args.length < 1 || args[ 0 ].equals( "-h" ) ) {
			printHelp();
			System.exit( 0 );
		}

		CmdLineParser cmdLineParser = new CmdLineParser();

		CmdLineParser.Option magnumConfOpt = cmdLineParser.addStringOption( 'm', "magnum_conf" );
		CmdLineParser.Option outfileOpt = cmdLineParser.addStringOption( 'o', "out" );	
		CmdLineParser.Option pepXMLOpt = cmdLineParser.addStringOption( 'p', "pepxml" );	
		CmdLineParser.Option fastaFileOpt = cmdLineParser.addStringOption( 'f', "fasta" );

		// parse command line options
		try { cmdLineParser.parse(args); }
		catch (IllegalOptionValueException e) {
			printHelp();
			System.exit( 1 );
		}
		catch (UnknownOptionException e) {
			printHelp();
			System.exit( 1 );
		}

		String magnumConfFilePath = (String)cmdLineParser.getOptionValue( magnumConfOpt );
		String outFilePath = (String)cmdLineParser.getOptionValue( outfileOpt );
		String pepXMLFilePath = (String)cmdLineParser.getOptionValue( pepXMLOpt );
		String fastaFilePath = (String)cmdLineParser.getOptionValue( fastaFileOpt );

		File magnumConfFile = new File( magnumConfFilePath );
		if( !magnumConfFile.exists() ) {
			System.err.println( "Could not find magnum conf file: " + magnumConfFilePath );
			System.exit( 1 );
		}

		File pepXMLFile = new File( pepXMLFilePath );
		if( !pepXMLFile.exists() ) {
			System.err.println( "Could not find pepXML file: " + pepXMLFilePath );
			System.exit( 1 );
		}

		File fastaFile = new File( fastaFilePath );
		if( !fastaFile.exists() ) {
			System.err.println( "Could not find fasta file: " + fastaFilePath );
			System.exit( 1 );
		}

		ConversionProgramInfo cpi = ConversionProgramInfo.createInstance( String.join( " ",  args ) );        

		ConversionParameters cp = new ConversionParameters();
		cp.setConversionProgramInfo( cpi );
		cp.setFastaFile( fastaFile );
		cp.setMagnumConfFile( magnumConfFile );
		cp.setPepXMLFile( pepXMLFile );
		cp.setLimelightXMLOutputFile( new File( outFilePath ) );

		ConverterRunner.createInstance().convertMagnumTPPToLimelightXML( cp );
		System.exit( 0 );
	}

	/**
	 * Print help to STD OUT
	 */
	public static void printHelp() {

		try( BufferedReader br = new BufferedReader( new InputStreamReader( MainProgram.class.getResourceAsStream( "help.txt" ) ) ) ) {

			String line = null;
			while ( ( line = br.readLine() ) != null )
				System.out.println( line );				

		} catch ( Exception e ) {
			System.out.println( "Error printing help." );
		}
	}

	/**
	 * Print runtime info to STD ERR
	 * @throws Exception 
	 */
	public static void printRuntimeInfo() throws Exception {

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
			throw e;
		}
	}

}
