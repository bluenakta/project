package ticketIssuance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

 @RestController
 public class TicketIssuanceController {

  @PutMapping("/ticketIssuances/cancel/{bookId}")
  public TicketIssuance payCanceled(@PathVariable("bookId") Long bookId) {

   TicketIssuance ti = new TicketIssuance();
   ti.setBookId(bookId);
   ti.setIssueStatus("Canceled");

   return ti;

  }



 }
