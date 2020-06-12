package org.yeastrc.limelight.xml.magnumtpp.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.yeastrc.limelight.xml.magnumtpp.objects.TPPPSM;
import org.yeastrc.limelight.xml.magnumtpp.objects.TPPReportedPeptide;

public class ReportedPeptideUtils {

	public static TPPReportedPeptide getTPPReportedPeptideForTPPPSM( TPPPSM psm ) throws Exception {
		
		TPPReportedPeptide rp = new TPPReportedPeptide();
		
		rp.setNakedPeptide( psm.getPeptideSequence() );
		rp.setMods( psm.getModifications() );
		rp.setReportedPeptideString( ModParsingUtils.getRoundedReportedPeptideString( psm.getPeptideSequence(), psm.getModifications() ));

		return rp;
	}

}
