package com.example.ags.query.patient;

import com.example.ags.api.CheckInWardCancelledEvent;
import com.example.ags.api.NotificationQuery;
import com.example.ags.api.PatientCreatedEvent;
import com.example.ags.api.WardCheckedInEvent;
import com.example.ags.query.PatientViewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class PatientProjector {

    private final PatientViewRepository repository;
    private final QueryUpdateEmitter emitter;

    @EventHandler
    public void on(PatientCreatedEvent evt) {
        log.info("PatientProjector: {}", evt);
        this.repository.save(new PatientView(evt.getHkid(), evt.getName(), null, null, null));
        emitter.emit(NotificationQuery.class, q -> true , evt.getHkid() + ":" + evt.getName());
    }

    @EventHandler
    public void on(WardCheckedInEvent evt) {
        log.info("PatientProjector: {}", evt);
        PatientView patientView = this.repository.findById(evt.getHkid()).orElseThrow();
        patientView.setHospCode(evt.getHospCode());
        patientView.setWardCode(evt.getWardCode());
        patientView.setBedNum(evt.getBedNum());
        emitter.emit(NotificationQuery.class, q -> true , evt.getHkid() + ":" + evt.getName() +
                " at " + evt.getHospCode() + ":" + evt.getWardCode());
    }

    @EventHandler
    public void on(CheckInWardCancelledEvent evt) {
        log.info("PatientProjector: {}", evt);
        PatientView patientView = this.repository.findById(evt.getHkid()).orElseThrow();
        patientView.setHospCode(null);
        patientView.setWardCode(null);
        patientView.setBedNum(null);
    }
}
