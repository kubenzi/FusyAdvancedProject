package com.codecool.keepcash.Service;

import com.codecool.keepcash.Exception.IdNotFoundException;
import com.codecool.keepcash.Repository.AccountTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountTypeServiceImplTest {

    @Mock
    private AccountTypeRepository accountTypeRepository;

    @InjectMocks
    private AccountTypeServiceImpl accountTypeService;

    @Test
    public void should_delete_account_type() {
        //given
        Long id = 1L;

        //when
        accountTypeService.deleteAccountTypeById(id);

        //then
        verify(accountTypeRepository, times(1)).deleteById(eq(id));
    }

    @Test(expected = IdNotFoundException.class)
    public void should_throw_exception_when_not_found() {
        //given
        Long id = 111111111L;

        //when
        doThrow(IdNotFoundException.class).when(accountTypeRepository).deleteById(id);

        //then
        accountTypeService.deleteAccountTypeById(id);
    }
}