package com.example.Bildergalerie.authority.authorityDTO;

import com.example.Bildergalerie.generic.ExtendedDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;


    public class AuthorityDTO extends ExtendedDTO {

        @NotNull
        @Size(min = 1, max = 255)
        private String name;

        public AuthorityDTO() {
        }

        public AuthorityDTO(UUID id, String name) {
            super(id);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public AuthorityDTO setName(String name) {
            this.name = name;
            return this;
        }
    }

