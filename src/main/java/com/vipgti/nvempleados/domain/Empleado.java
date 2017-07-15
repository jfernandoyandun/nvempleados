package com.vipgti.nvempleados.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Empleado.
 */
@Entity
@Table(name = "empleado")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "identification_number", length = 50, nullable = false)
    private String identificationNumber;

    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "first_names", length = 80, nullable = false)
    private String firstNames;

    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "last_names", length = 80, nullable = false)
    private String lastNames;

    @NotNull
    @Column(name = "entry_date", nullable = false)
    private LocalDate entryDate;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "monthly_salary", nullable = false)
    private Float monthlySalary;

    @DecimalMin(value = "0")
    @Column(name = "other_income")
    private Float otherIncome;

    @NotNull
    @Column(name = "total_income", nullable = false)
    private Float totalIncome;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "yearly_expense", nullable = false)
    private Float yearlyExpense;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "monthly_expense", nullable = false)
    private Float monthlyExpense;

    @NotNull
    @Column(name = "total_value", nullable = false)
    private Float totalValue;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "assets", nullable = false)
    private Float assets;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "liabilities", nullable = false)
    private Float liabilities;

    @NotNull
    @Column(name = "wealth", nullable = false)
    private Float wealth;

    @NotNull
    @Column(name = "relationship", nullable = false)
    private Float relationship;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public Empleado identificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
        return this;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getFirstNames() {
        return firstNames;
    }

    public Empleado firstNames(String firstNames) {
        this.firstNames = firstNames;
        return this;
    }

    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }

    public String getLastNames() {
        return lastNames;
    }

    public Empleado lastNames(String lastNames) {
        this.lastNames = lastNames;
        return this;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public Empleado entryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
        return this;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public Float getMonthlySalary() {
        return monthlySalary;
    }

    public Empleado monthlySalary(Float monthlySalary) {
        this.monthlySalary = monthlySalary;
        return this;
    }

    public void setMonthlySalary(Float monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public Float getOtherIncome() {
        return otherIncome;
    }

    public Empleado otherIncome(Float otherIncome) {
        this.otherIncome = otherIncome;
        return this;
    }

    public void setOtherIncome(Float otherIncome) {
        this.otherIncome = otherIncome;
    }

    public Float getTotalIncome() {
        return totalIncome;
    }

    public Empleado totalIncome(Float totalIncome) {
        this.totalIncome = totalIncome;
        return this;
    }

    public void setTotalIncome(Float totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Float getYearlyExpense() {
        return yearlyExpense;
    }

    public Empleado yearlyExpense(Float yearlyExpense) {
        this.yearlyExpense = yearlyExpense;
        return this;
    }

    public void setYearlyExpense(Float yearlyExpense) {
        this.yearlyExpense = yearlyExpense;
    }

    public Float getMonthlyExpense() {
        return monthlyExpense;
    }

    public Empleado monthlyExpense(Float monthlyExpense) {
        this.monthlyExpense = monthlyExpense;
        return this;
    }

    public void setMonthlyExpense(Float monthlyExpense) {
        this.monthlyExpense = monthlyExpense;
    }

    public Float getTotalValue() {
        return totalValue;
    }

    public Empleado totalValue(Float totalValue) {
        this.totalValue = totalValue;
        return this;
    }

    public void setTotalValue(Float totalValue) {
        this.totalValue = totalValue;
    }

    public Float getAssets() {
        return assets;
    }

    public Empleado assets(Float assets) {
        this.assets = assets;
        return this;
    }

    public void setAssets(Float assets) {
        this.assets = assets;
    }

    public Float getLiabilities() {
        return liabilities;
    }

    public Empleado liabilities(Float liabilities) {
        this.liabilities = liabilities;
        return this;
    }

    public void setLiabilities(Float liabilities) {
        this.liabilities = liabilities;
    }

    public Float getWealth() {
        return wealth;
    }

    public Empleado wealth(Float wealth) {
        this.wealth = wealth;
        return this;
    }

    public void setWealth(Float wealth) {
        this.wealth = wealth;
    }

    public Float getRelationship() {
        return relationship;
    }

    public Empleado relationship(Float relationship) {
        this.relationship = relationship;
        return this;
    }

    public void setRelationship(Float relationship) {
        this.relationship = relationship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Empleado empleado = (Empleado) o;
        if (empleado.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, empleado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Empleado{" +
            "id=" + id +
            ", identificationNumber='" + identificationNumber + "'" +
            ", firstNames='" + firstNames + "'" +
            ", lastNames='" + lastNames + "'" +
            ", entryDate='" + entryDate + "'" +
            ", monthlySalary='" + monthlySalary + "'" +
            ", otherIncome='" + otherIncome + "'" +
            ", totalIncome='" + totalIncome + "'" +
            ", yearlyExpense='" + yearlyExpense + "'" +
            ", monthlyExpense='" + monthlyExpense + "'" +
            ", totalValue='" + totalValue + "'" +
            ", assets='" + assets + "'" +
            ", liabilities='" + liabilities + "'" +
            ", wealth='" + wealth + "'" +
            ", relationship='" + relationship + "'" +
            '}';
    }
}
