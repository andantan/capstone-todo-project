package org.zerock.todoserviceproject.domain.service.module.remove;


import java.util.Map;

// +-+-+-+-+-+-+-+
//     REMOVE    |
// +-+-+-+-+-+-+-+
public interface TodoRemoveService {

    Map<String, String> requestRemove(Long tno);

}
