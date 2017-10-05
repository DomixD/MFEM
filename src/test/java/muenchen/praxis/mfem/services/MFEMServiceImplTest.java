package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.MfemApplicationTests;
import muenchen.praxis.mfem.entities.*;
import muenchen.praxis.mfem.persistence.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MFEMServiceImplTest extends MfemApplicationTests{

    @Mock
    private RepoCategory repoCategory;
    @Mock
    private RepoFramework repoFramework;
    @Mock
    private RepoClassification repoClassification;
    @Mock
    private RepoFrameworkEvaluation repoFrameworkEvaluation;
    @Mock
    private RepoFEvaResult repoFEvaResult;

    @InjectMocks
    private IMFEMService service = new MFEMServiceImpl();;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetResult() {
        super.setUpGetRes();
        when(repoCategory.findAll()).thenReturn(super.getCategories());
        when(repoFramework.findOne(anyInt())).thenReturn(super.getFramework());
        when(repoClassification.findOne(anyInt())).thenReturn(super.getClassification());
        when(repoFrameworkEvaluation.findByFrameworkInAndClassificationIn(any(Framework.class), any(Classification.class))).thenReturn(super.getFrameworkEvaluation());
        when(repoFEvaResult.findByFrameworkEvaluation(any(FrameworkEvaluation.class))).thenReturn(super.getResults());
        List<Double> result = service.getResult(1, 1);
        assertEquals(new ArrayList<Double>(Arrays.asList(1.0, 0.5, 0.0, 1.0, 0.5, 0.0)), result);
    }

    @Test
    public void testGetFrames() {
        super.setUpGetFrames();
        when(repoClassification.findOne(anyInt())).thenReturn(super.getClassification());
        when(repoFrameworkEvaluation.findByClassification(any(Classification.class))).thenReturn(super.getFrameworkEvaluations());
        List<Integer> result = service.getFrames(1);
        assertEquals(new ArrayList<Integer>(Arrays.asList(1, 2, 3)), result);
    }

}
