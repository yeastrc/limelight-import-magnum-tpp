package org.yeastrc.limelight.xml.magnumtpp.builder;

import org.yeastrc.limelight.limelight_import.api.xml_dto.LimelightInput;
import org.yeastrc.limelight.xml.magnumtpp.objects.TPPReportedPeptide;
import org.yeastrc.proteomics.fasta.FASTAEntry;
import org.yeastrc.proteomics.fasta.FASTAFileParser;
import org.yeastrc.proteomics.fasta.FASTAFileParserFactory;
import org.yeastrc.proteomics.fasta.FASTAHeader;

import java.io.File;
import java.util.Collection;
import java.util.Map;

public class FastaFileStatisticsBuilder {

    public static FastaFileStatisticsBuilder getInstance() { return new FastaFileStatisticsBuilder(); }

    /**
     * Add all target proteins from the FASTA file that contain any of the peptides found in the experiment
     * to the limelight xml document in the matched proteins section.
     *
     * @param limelightInputRoot
     * @param fastaFile
     * @throws Exception
     */
    public void buildFastaFileStatistics(LimelightInput limelightInputRoot, File fastaFile, String decoyPrefix, String independentDecoyPrefix ) throws Exception {

        System.err.println( " Gathering FASTA file statistics..." );



    }

    private FastaFileStatistics getFastaFileStatistics(File fastaFile, String decoyPrefix, String independentDecoyPrefix) throws Exception {

        int targetCount = 0;
        int decoyCount = 0;
        int independentDecoyCount = 0;

        System.err.println( " Gathering FASTA file statistics..." );

        try ( FASTAFileParser parser = FASTAFileParserFactory.getInstance().getFASTAFileParser( fastaFile ) ) {

            int count = 0;
            System.err.println("");

            for (FASTAEntry entry = parser.getNextEntry(); entry != null; entry = parser.getNextEntry()) {
                if(fastaEntryIsDecoy(entry, decoyPrefix)) {
                    decoyCount++;
                } else if(fastaEntryIsIndependentDecoy(entry, independentDecoyPrefix)) {
                    independentDecoyCount++;
                } else {
                    targetCount++;
                }
            }
        }

        // todo: should i really do sha384 hash?

    }

    /**
     * Return true if this FASTA sequence (entry) is a decoy. It is a decoy if all annotations for this
     * sequence in this FASTA file start with the decoy prefix. Will return false if there is no decoy
     * prefix.
     *
     * @param entry
     * @param decoyPrefix
     * @return
     */
    private boolean fastaEntryIsDecoy(FASTAEntry entry, String decoyPrefix) {
        if(decoyPrefix == null) { return false; }

        for(FASTAHeader header : entry.getHeaders()) {
            if(!(header.getName().startsWith(decoyPrefix))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Return true if this FASTA sequence (entry) is an independent decoy. It is an independent decoy if all
     * annotations for this sequence in this FASTA file start with the independent decoy prefix. Will return
     * false if there is no independent decoy prefix.
     *
     * @param entry
     * @param independentDecoyPrefix
     * @return
     */
    private boolean fastaEntryIsIndependentDecoy(FASTAEntry entry, String independentDecoyPrefix) {
        if(independentDecoyPrefix == null) { return false; }

        for(FASTAHeader header : entry.getHeaders()) {
            if(!(header.getName().startsWith(independentDecoyPrefix))) {
                return false;
            }
        }

        return true;
    }


    private class FastaFileStatistics {
        private int targetCount;
        private int decoyCount;
        private int independentDecoyCount;

        public void setTargetCount(int targetCount) {
            this.targetCount = targetCount;
        }

        public void setDecoyCount(int decoyCount) {
            this.decoyCount = decoyCount;
        }

        public void setIndependentDecoyCount(int independentDecoyCount) {
            this.independentDecoyCount = independentDecoyCount;
        }
    }


}
