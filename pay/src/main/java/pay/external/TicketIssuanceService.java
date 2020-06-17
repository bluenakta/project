
package pay.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pay.Cancelled;

@FeignClient(name="ticketIssuance", url="http://localhost:8088")
public interface TicketIssuanceService {

    @RequestMapping(method= RequestMethod.PUT, value="/ticketIssuances/{bookId}" )
    public void ticketIssue(@PathVariable("bookId") final Long bookId, @RequestBody TicketIssuance ticketIssuance);

}
