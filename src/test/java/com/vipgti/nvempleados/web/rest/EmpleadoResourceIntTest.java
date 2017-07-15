package com.vipgti.nvempleados.web.rest;

import com.vipgti.nvempleados.NvempleadosApp;

import com.vipgti.nvempleados.domain.Empleado;
import com.vipgti.nvempleados.repository.EmpleadoRepository;
import com.vipgti.nvempleados.service.EmpleadoService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EmpleadoResource REST controller.
 *
 * @see EmpleadoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NvempleadosApp.class)
public class EmpleadoResourceIntTest {

    private static final String DEFAULT_IDENTIFICATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFICATION_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAMES = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAMES = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAMES = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAMES = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ENTRY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENTRY_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Float DEFAULT_MONTHLY_SALARY = 0F;
    private static final Float UPDATED_MONTHLY_SALARY = 1F;

    private static final Float DEFAULT_OTHER_INCOME = 0F;
    private static final Float UPDATED_OTHER_INCOME = 1F;

    private static final Float DEFAULT_TOTAL_INCOME = 1F;
    private static final Float UPDATED_TOTAL_INCOME = 2F;

    private static final Float DEFAULT_YEARLY_EXPENSE = 0F;
    private static final Float UPDATED_YEARLY_EXPENSE = 1F;

    private static final Float DEFAULT_MONTHLY_EXPENSE = 0F;
    private static final Float UPDATED_MONTHLY_EXPENSE = 1F;

    private static final Float DEFAULT_TOTAL_VALUE = 1F;
    private static final Float UPDATED_TOTAL_VALUE = 2F;

    private static final Float DEFAULT_ASSETS = 0F;
    private static final Float UPDATED_ASSETS = 1F;

    private static final Float DEFAULT_LIABILITIES = 0F;
    private static final Float UPDATED_LIABILITIES = 1F;

    private static final Float DEFAULT_WEALTH = 1F;
    private static final Float UPDATED_WEALTH = 2F;

    private static final Float DEFAULT_RELATIONSHIP = 1F;
    private static final Float UPDATED_RELATIONSHIP = 2F;

    @Inject
    private EmpleadoRepository empleadoRepository;

    @Inject
    private EmpleadoService empleadoService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restEmpleadoMockMvc;

    private Empleado empleado;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EmpleadoResource empleadoResource = new EmpleadoResource();
        ReflectionTestUtils.setField(empleadoResource, "empleadoService", empleadoService);
        this.restEmpleadoMockMvc = MockMvcBuilders.standaloneSetup(empleadoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Empleado createEntity(EntityManager em) {
        Empleado empleado = new Empleado()
                .identificationNumber(DEFAULT_IDENTIFICATION_NUMBER)
                .firstNames(DEFAULT_FIRST_NAMES)
                .lastNames(DEFAULT_LAST_NAMES)
                .entryDate(DEFAULT_ENTRY_DATE)
                .monthlySalary(DEFAULT_MONTHLY_SALARY)
                .otherIncome(DEFAULT_OTHER_INCOME)
                .totalIncome(DEFAULT_TOTAL_INCOME)
                .yearlyExpense(DEFAULT_YEARLY_EXPENSE)
                .monthlyExpense(DEFAULT_MONTHLY_EXPENSE)
                .totalValue(DEFAULT_TOTAL_VALUE)
                .assets(DEFAULT_ASSETS)
                .liabilities(DEFAULT_LIABILITIES)
                .wealth(DEFAULT_WEALTH)
                .relationship(DEFAULT_RELATIONSHIP);
        return empleado;
    }

    @Before
    public void initTest() {
        empleado = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmpleado() throws Exception {
        int databaseSizeBeforeCreate = empleadoRepository.findAll().size();

        // Create the Empleado

        restEmpleadoMockMvc.perform(post("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isCreated());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeCreate + 1);
        Empleado testEmpleado = empleadoList.get(empleadoList.size() - 1);
        assertThat(testEmpleado.getIdentificationNumber()).isEqualTo(DEFAULT_IDENTIFICATION_NUMBER);
        assertThat(testEmpleado.getFirstNames()).isEqualTo(DEFAULT_FIRST_NAMES);
        assertThat(testEmpleado.getLastNames()).isEqualTo(DEFAULT_LAST_NAMES);
        assertThat(testEmpleado.getEntryDate()).isEqualTo(DEFAULT_ENTRY_DATE);
        assertThat(testEmpleado.getMonthlySalary()).isEqualTo(DEFAULT_MONTHLY_SALARY);
        assertThat(testEmpleado.getOtherIncome()).isEqualTo(DEFAULT_OTHER_INCOME);
        assertThat(testEmpleado.getTotalIncome()).isEqualTo(DEFAULT_TOTAL_INCOME);
        assertThat(testEmpleado.getYearlyExpense()).isEqualTo(DEFAULT_YEARLY_EXPENSE);
        assertThat(testEmpleado.getMonthlyExpense()).isEqualTo(DEFAULT_MONTHLY_EXPENSE);
        assertThat(testEmpleado.getTotalValue()).isEqualTo(DEFAULT_TOTAL_VALUE);
        assertThat(testEmpleado.getAssets()).isEqualTo(DEFAULT_ASSETS);
        assertThat(testEmpleado.getLiabilities()).isEqualTo(DEFAULT_LIABILITIES);
        assertThat(testEmpleado.getWealth()).isEqualTo(DEFAULT_WEALTH);
        assertThat(testEmpleado.getRelationship()).isEqualTo(DEFAULT_RELATIONSHIP);
    }

    @Test
    @Transactional
    public void createEmpleadoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = empleadoRepository.findAll().size();

        // Create the Empleado with an existing ID
        Empleado existingEmpleado = new Empleado();
        existingEmpleado.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmpleadoMockMvc.perform(post("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingEmpleado)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdentificationNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setIdentificationNumber(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc.perform(post("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstNamesIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setFirstNames(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc.perform(post("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNamesIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setLastNames(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc.perform(post("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEntryDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setEntryDate(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc.perform(post("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMonthlySalaryIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setMonthlySalary(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc.perform(post("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalIncomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setTotalIncome(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc.perform(post("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkYearlyExpenseIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setYearlyExpense(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc.perform(post("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMonthlyExpenseIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setMonthlyExpense(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc.perform(post("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setTotalValue(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc.perform(post("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAssetsIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setAssets(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc.perform(post("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLiabilitiesIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setLiabilities(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc.perform(post("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWealthIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setWealth(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc.perform(post("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRelationshipIsRequired() throws Exception {
        int databaseSizeBeforeTest = empleadoRepository.findAll().size();
        // set the field null
        empleado.setRelationship(null);

        // Create the Empleado, which fails.

        restEmpleadoMockMvc.perform(post("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isBadRequest());

        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEmpleados() throws Exception {
        // Initialize the database
        empleadoRepository.saveAndFlush(empleado);

        // Get all the empleadoList
        restEmpleadoMockMvc.perform(get("/api/empleados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(empleado.getId().intValue())))
            .andExpect(jsonPath("$.[*].identificationNumber").value(hasItem(DEFAULT_IDENTIFICATION_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].firstNames").value(hasItem(DEFAULT_FIRST_NAMES.toString())))
            .andExpect(jsonPath("$.[*].lastNames").value(hasItem(DEFAULT_LAST_NAMES.toString())))
            .andExpect(jsonPath("$.[*].entryDate").value(hasItem(DEFAULT_ENTRY_DATE.toString())))
            .andExpect(jsonPath("$.[*].monthlySalary").value(hasItem(DEFAULT_MONTHLY_SALARY.doubleValue())))
            .andExpect(jsonPath("$.[*].otherIncome").value(hasItem(DEFAULT_OTHER_INCOME.doubleValue())))
            .andExpect(jsonPath("$.[*].totalIncome").value(hasItem(DEFAULT_TOTAL_INCOME.doubleValue())))
            .andExpect(jsonPath("$.[*].yearlyExpense").value(hasItem(DEFAULT_YEARLY_EXPENSE.doubleValue())))
            .andExpect(jsonPath("$.[*].monthlyExpense").value(hasItem(DEFAULT_MONTHLY_EXPENSE.doubleValue())))
            .andExpect(jsonPath("$.[*].totalValue").value(hasItem(DEFAULT_TOTAL_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].assets").value(hasItem(DEFAULT_ASSETS.doubleValue())))
            .andExpect(jsonPath("$.[*].liabilities").value(hasItem(DEFAULT_LIABILITIES.doubleValue())))
            .andExpect(jsonPath("$.[*].wealth").value(hasItem(DEFAULT_WEALTH.doubleValue())))
            .andExpect(jsonPath("$.[*].relationship").value(hasItem(DEFAULT_RELATIONSHIP.doubleValue())));
    }

    @Test
    @Transactional
    public void getEmpleado() throws Exception {
        // Initialize the database
        empleadoRepository.saveAndFlush(empleado);

        // Get the empleado
        restEmpleadoMockMvc.perform(get("/api/empleados/{id}", empleado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(empleado.getId().intValue()))
            .andExpect(jsonPath("$.identificationNumber").value(DEFAULT_IDENTIFICATION_NUMBER.toString()))
            .andExpect(jsonPath("$.firstNames").value(DEFAULT_FIRST_NAMES.toString()))
            .andExpect(jsonPath("$.lastNames").value(DEFAULT_LAST_NAMES.toString()))
            .andExpect(jsonPath("$.entryDate").value(DEFAULT_ENTRY_DATE.toString()))
            .andExpect(jsonPath("$.monthlySalary").value(DEFAULT_MONTHLY_SALARY.doubleValue()))
            .andExpect(jsonPath("$.otherIncome").value(DEFAULT_OTHER_INCOME.doubleValue()))
            .andExpect(jsonPath("$.totalIncome").value(DEFAULT_TOTAL_INCOME.doubleValue()))
            .andExpect(jsonPath("$.yearlyExpense").value(DEFAULT_YEARLY_EXPENSE.doubleValue()))
            .andExpect(jsonPath("$.monthlyExpense").value(DEFAULT_MONTHLY_EXPENSE.doubleValue()))
            .andExpect(jsonPath("$.totalValue").value(DEFAULT_TOTAL_VALUE.doubleValue()))
            .andExpect(jsonPath("$.assets").value(DEFAULT_ASSETS.doubleValue()))
            .andExpect(jsonPath("$.liabilities").value(DEFAULT_LIABILITIES.doubleValue()))
            .andExpect(jsonPath("$.wealth").value(DEFAULT_WEALTH.doubleValue()))
            .andExpect(jsonPath("$.relationship").value(DEFAULT_RELATIONSHIP.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEmpleado() throws Exception {
        // Get the empleado
        restEmpleadoMockMvc.perform(get("/api/empleados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmpleado() throws Exception {
        // Initialize the database
        empleadoService.save(empleado);

        int databaseSizeBeforeUpdate = empleadoRepository.findAll().size();

        // Update the empleado
        Empleado updatedEmpleado = empleadoRepository.findOne(empleado.getId());
        updatedEmpleado
                .identificationNumber(UPDATED_IDENTIFICATION_NUMBER)
                .firstNames(UPDATED_FIRST_NAMES)
                .lastNames(UPDATED_LAST_NAMES)
                .entryDate(UPDATED_ENTRY_DATE)
                .monthlySalary(UPDATED_MONTHLY_SALARY)
                .otherIncome(UPDATED_OTHER_INCOME)
                .totalIncome(UPDATED_TOTAL_INCOME)
                .yearlyExpense(UPDATED_YEARLY_EXPENSE)
                .monthlyExpense(UPDATED_MONTHLY_EXPENSE)
                .totalValue(UPDATED_TOTAL_VALUE)
                .assets(UPDATED_ASSETS)
                .liabilities(UPDATED_LIABILITIES)
                .wealth(UPDATED_WEALTH)
                .relationship(UPDATED_RELATIONSHIP);

        restEmpleadoMockMvc.perform(put("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEmpleado)))
            .andExpect(status().isOk());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeUpdate);
        Empleado testEmpleado = empleadoList.get(empleadoList.size() - 1);
        assertThat(testEmpleado.getIdentificationNumber()).isEqualTo(UPDATED_IDENTIFICATION_NUMBER);
        assertThat(testEmpleado.getFirstNames()).isEqualTo(UPDATED_FIRST_NAMES);
        assertThat(testEmpleado.getLastNames()).isEqualTo(UPDATED_LAST_NAMES);
        assertThat(testEmpleado.getEntryDate()).isEqualTo(UPDATED_ENTRY_DATE);
        assertThat(testEmpleado.getMonthlySalary()).isEqualTo(UPDATED_MONTHLY_SALARY);
        assertThat(testEmpleado.getOtherIncome()).isEqualTo(UPDATED_OTHER_INCOME);
        assertThat(testEmpleado.getTotalIncome()).isEqualTo(UPDATED_TOTAL_INCOME);
        assertThat(testEmpleado.getYearlyExpense()).isEqualTo(UPDATED_YEARLY_EXPENSE);
        assertThat(testEmpleado.getMonthlyExpense()).isEqualTo(UPDATED_MONTHLY_EXPENSE);
        assertThat(testEmpleado.getTotalValue()).isEqualTo(UPDATED_TOTAL_VALUE);
        assertThat(testEmpleado.getAssets()).isEqualTo(UPDATED_ASSETS);
        assertThat(testEmpleado.getLiabilities()).isEqualTo(UPDATED_LIABILITIES);
        assertThat(testEmpleado.getWealth()).isEqualTo(UPDATED_WEALTH);
        assertThat(testEmpleado.getRelationship()).isEqualTo(UPDATED_RELATIONSHIP);
    }

    @Test
    @Transactional
    public void updateNonExistingEmpleado() throws Exception {
        int databaseSizeBeforeUpdate = empleadoRepository.findAll().size();

        // Create the Empleado

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmpleadoMockMvc.perform(put("/api/empleados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(empleado)))
            .andExpect(status().isCreated());

        // Validate the Empleado in the database
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEmpleado() throws Exception {
        // Initialize the database
        empleadoService.save(empleado);

        int databaseSizeBeforeDelete = empleadoRepository.findAll().size();

        // Get the empleado
        restEmpleadoMockMvc.perform(delete("/api/empleados/{id}", empleado.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Empleado> empleadoList = empleadoRepository.findAll();
        assertThat(empleadoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
