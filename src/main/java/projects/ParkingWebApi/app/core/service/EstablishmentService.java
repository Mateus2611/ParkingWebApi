package projects.ParkingWebApi.app.core.service;

import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.validation.CNPJValidator;
import ch.qos.logback.core.joran.conditional.IfAction;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.stereotype.Service;
import projects.ParkingWebApi.app.core.exception.InvalidTelephoneNumberException;
import projects.ParkingWebApi.app.core.model.Establishment;
import projects.ParkingWebApi.app.core.model.dto.EstablishmentDTO;
import projects.ParkingWebApi.app.infrastructure.repository.IEstablishmentRepository;

import java.util.List;

@Service
public class EstablishmentService {

    private final IEstablishmentRepository repository;
    private final CNPJValidator cnpjValidator = new CNPJValidator();
    private final CNPJFormatter cnpjFormatter = new CNPJFormatter();
    private final PhoneNumberUtil phoneValidator = PhoneNumberUtil.getInstance();


    public EstablishmentService(IEstablishmentRepository repository) {
        this.repository = repository;
    }


    public Establishment save(EstablishmentDTO dto) throws NumberParseException {
        cnpjValidator.assertValid(cnpjFormatter.unformat(dto.cnpj));
        Phonenumber.PhoneNumber phone = phoneValidator.parse(dto.telephone, "BR");

        if (!phoneValidator.isValidNumber(phone))
            throw new InvalidTelephoneNumberException(dto.telephone);

        return repository.save(dto.mapper());
    }
}
