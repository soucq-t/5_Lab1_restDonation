package com.example.donation.presentation;

import com.example.donation.domain.Donation;
import com.example.donation.domain.Person;
import com.example.donation.domain.exception.NoSuchDonationException;
import com.example.donation.domain.exception.NoSuchPersonException;
import com.example.donation.persistence.DonationRepository;
import com.example.donation.persistence.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
public record DonationsController(DonationRepository donationRepository, PersonRepository personRepository) {

    @GetMapping("donations/")
    public List<Person> getMindDonated(@RequestParam(value = "min") int minDonation) {
        return donationRepository.getMindDonated(minDonation);
    }

    @GetMapping("donations/{id}")
    public Donation getDonationById(@PathVariable long id) {
        return donationRepository.getDonationById(id).orElseThrow(NoSuchDonationException::new);
    }

    @GetMapping("persons/{id}/donations")
    public List<Donation> getAllDonationsOfAPerson(@PathVariable long id) {
        if(personRepository.findPersonById(id).isEmpty()){
            throw new NoSuchDonationException();
        }

        return donationRepository.findAllDonationsOfAPerson(id).orElseThrow(NoSuchPersonException::new);
    }

    @PostMapping("persons/{id}/donations")
    public ResponseEntity<Donation> addADOnation(@PathVariable long id, @RequestBody @Valid Donation donation) {
        try {
            donation.setPerson(personRepository.findPersonById(id).orElseThrow(NoSuchPersonException::new));
            var saved = donationRepository.save(donation);
            var uri = ServletUriComponentsBuilder
                    .fromCurrentRequestUri()
                    .path("/{id}")
                    .build(saved.getId());

            return ResponseEntity.created(uri).body(saved);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
