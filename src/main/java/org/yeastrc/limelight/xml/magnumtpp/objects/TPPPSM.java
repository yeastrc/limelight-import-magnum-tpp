package org.yeastrc.limelight.xml.magnumtpp.objects;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

public class TPPPSM {

	private BigDecimal mScore;
	private BigDecimal dScore;
	private BigDecimal eValue;
	private BigDecimal ppmError;
	private BigDecimal massDiff;
	private Collection<BigDecimal> reporterIons;
	private boolean isDecoy = false;
	private boolean isIndependentDecoy = false;

	@Override
	public String toString() {
		return "TPPPSM{" +
				"mScore=" + mScore +
				", dScore=" + dScore +
				", eValue=" + eValue +
				", ppmError=" + ppmError +
				", massDiff=" + massDiff +
				", reporterIons=" + reporterIons +
				", isDecoy=" + isDecoy +
				", isIndependentDecoy=" + isIndependentDecoy +
				", peptideProphetProbability=" + peptideProphetProbability +
				", interProphetProbability=" + interProphetProbability +
				", scanNumber=" + scanNumber +
				", precursorNeutralMass=" + precursorNeutralMass +
				", charge=" + charge +
				", retentionTime=" + retentionTime +
				", peptideSequence='" + peptideSequence + '\'' +
				", openModification=" + openModification +
				", modifications=" + modifications +
				'}';
	}

	public Boolean getDecoy() {
		return isDecoy;
	}

	public void setDecoy(Boolean decoy) {
		isDecoy = decoy;
	}

	public Boolean getIndependentDecoy() {
		return isIndependentDecoy;
	}

	public void setIndependentDecoy(Boolean independentDecoy) {
		isIndependentDecoy = independentDecoy;
	}

	public BigDecimal getMassDiff() {
		return massDiff;
	}

	public void setMassDiff(BigDecimal massDiff) {
		this.massDiff = massDiff;
	}

	private BigDecimal peptideProphetProbability;
	private BigDecimal interProphetProbability;
	
	private int scanNumber;
	private BigDecimal precursorNeutralMass;
	private int charge;
	private BigDecimal retentionTime;
	
	private String peptideSequence;

	private OpenModification openModification;

	public OpenModification getOpenModification() {
		return openModification;
	}

	public void setOpenModification(OpenModification openModification) {
		this.openModification = openModification;
	}

	private Map<Integer,BigDecimal> modifications;

	public Collection<BigDecimal> getReporterIons() {
		return reporterIons;
	}

	public void setReporterIons(Collection<BigDecimal> reporterIons) {
		this.reporterIons = reporterIons;
	}

	public BigDecimal getPpmError() {
        return ppmError;
    }

    public void setPpmError(BigDecimal ppmError) {
        this.ppmError = ppmError;
    }

    public BigDecimal getmScore() {
		return mScore;
	}

	public void setmScore(BigDecimal mScore) {
		this.mScore = mScore;
	}

	public BigDecimal getdScore() {
		return dScore;
	}

	public void setdScore(BigDecimal dScore) {
		this.dScore = dScore;
	}

	public BigDecimal geteValue() {
		return eValue;
	}

	public void seteValue(BigDecimal eValue) {
		this.eValue = eValue;
	}

	public BigDecimal getPeptideProphetProbability() {
		return peptideProphetProbability;
	}

	public void setPeptideProphetProbability(BigDecimal peptideProphetProbability) {
		this.peptideProphetProbability = peptideProphetProbability;
	}

	public BigDecimal getInterProphetProbability() {
		return interProphetProbability;
	}

	public void setInterProphetProbability(BigDecimal interProphetProbability) {
		this.interProphetProbability = interProphetProbability;
	}


	public int getScanNumber() {
		return scanNumber;
	}

	public void setScanNumber(int scanNumber) {
		this.scanNumber = scanNumber;
	}

	public BigDecimal getPrecursorNeutralMass() {
		return precursorNeutralMass;
	}

	public void setPrecursorNeutralMass(BigDecimal precursorNeutralMass) {
		this.precursorNeutralMass = precursorNeutralMass;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public BigDecimal getRetentionTime() {
		return retentionTime;
	}

	public void setRetentionTime(BigDecimal retentionTime) {
		this.retentionTime = retentionTime;
	}

	public String getPeptideSequence() {
		return peptideSequence;
	}

	public void setPeptideSequence(String peptideSequence) {
		this.peptideSequence = peptideSequence;
	}

	public Map<Integer, BigDecimal> getModifications() {
		return modifications;
	}

	public void setModifications(Map<Integer, BigDecimal> modifications) {
		this.modifications = modifications;
	}

}
