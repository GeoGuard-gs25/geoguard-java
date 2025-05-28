package global.geoguard.controller;

import global.geoguard.model.EnergyBill;
import global.geoguard.model.User;
import global.geoguard.repository.EnergyBillRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bills")
public class EnergyBillController {

    @Autowired
    private EnergyBillRepository energyBillRepository;

    @GetMapping
    public Page<EnergyBill> getUserBills(@AuthenticationPrincipal User user, Pageable pageable) {
        return energyBillRepository.findByOwnerId(user.getId(), pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnergyBill> getBillById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Optional<EnergyBill> bill = energyBillRepository.findById(id);

        if (bill.isPresent() && bill.get().getOwner().getId().equals(user.getId())) {
            return ResponseEntity.ok(bill.get());
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping
    public EnergyBill createBill(@RequestBody @Valid EnergyBill energyBill, @AuthenticationPrincipal User user) {
        energyBill.setOwner(user);
        return energyBillRepository.save(energyBill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnergyBill> updateBill(@PathVariable Long id, @RequestBody @Valid EnergyBill updatedBill,
            @AuthenticationPrincipal User user) {
        Optional<EnergyBill> existing = energyBillRepository.findById(id);

        if (existing.isPresent() && existing.get().getOwner().getId().equals(user.getId())) {
            EnergyBill bill = existing.get();
            bill.setConsumoKwh(updatedBill.getConsumoKwh());
            bill.setValorKwh(updatedBill.getValorKwh());
            bill.setAmount(updatedBill.getAmount());
            bill.setMonth(updatedBill.getMonth());
            return ResponseEntity.ok(energyBillRepository.save(bill));
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Optional<EnergyBill> bill = energyBillRepository.findById(id);

        if (bill.isPresent() && bill.get().getOwner().getId().equals(user.getId())) {
            energyBillRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(403).build();
        }
    }
}
