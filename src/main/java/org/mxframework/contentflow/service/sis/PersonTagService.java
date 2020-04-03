package org.mxframework.contentflow.service.sis;

import org.mxframework.contentflow.domain.model.sis.identity.Person;
import org.mxframework.contentflow.domain.model.sis.tag.PersonTag;
import org.mxframework.contentflow.domain.model.sis.tag.TagId;
import org.mxframework.contentflow.domain.model.sis.tag.PersonTagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @author mx
 */
@Service
public class PersonTagService {

    private final PersonTagRepository personTagRepository;

    public PersonTagService(PersonTagRepository personTagRepository) {
        this.personTagRepository = personTagRepository;
    }

    public List<PersonTag> listPeopleOfTagId(TagId tagId) {
        return (List<PersonTag>) personTagRepository.personTagsOfTagId(tagId);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void add(PersonTag personTag) {
        personTagRepository.add(personTag);
    }

    public void saveInAbsent(Collection<PersonTag> personTagCollection) {
        for (PersonTag personTag : personTagCollection) {
            PersonTag bytp = personTagRepository.personTagOfPersonAndTagId(personTag.person(), personTag.tagId());
            if (bytp == null) {
                personTagRepository.add(personTag);
            }
        }
    }

    public PersonTag getByPersonAndTagId(Person person, TagId tagId) {
        return personTagRepository.personTagOfPersonAndTagId(person, tagId);
    }

    public List<PersonTag> listByPerson(Person person) {
        return (List<PersonTag>) personTagRepository.personTagsOfPerson(person);
    }

    public Integer getPersonCounterByTagId(TagId tagId) {
        List<PersonTag> personTagList = (List<PersonTag>) personTagRepository.personTagsOfTagId(tagId);
        return personTagList != null ? personTagList.size() : 0;
    }
}
