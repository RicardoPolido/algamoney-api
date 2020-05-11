package com.algaworks.algamoney.api.event.listener;

import com.algaworks.algamoney.api.event.CreatedResourceEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class CreatedResourceListener implements ApplicationListener<CreatedResourceEvent> {

    @Override
    public void onApplicationEvent(CreatedResourceEvent createdResourceEvent) {
        HttpServletResponse response = createdResourceEvent.getResponse();
        Long id = createdResourceEvent.getId();

        addHeaderLocation(id, response);
    }

    private void addHeaderLocation(Long id, HttpServletResponse response) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
