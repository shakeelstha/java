

import java.util.Date;

import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;

import com.adyen.services.common.Amount;
import com.adyen.services.payment.AnyType2AnyTypeMapEntry;
import com.adyen.services.payment.Card;
import com.adyen.services.payment.ModificationRequest;
import com.adyen.services.payment.ModificationResult;
import com.adyen.services.payment.PaymentLocator;
import com.adyen.services.payment.PaymentPortType;
import com.adyen.services.payment.PaymentRequest;
import com.adyen.services.payment.PaymentResult;
import com.adyen.services.payment.Recurring;


public class Modification {
    
	public static void main ( String[] args ) {
		try {
			
			PaymentPortType ws = new PaymentLocator().getPaymentHttpPort( new java.net.URL( Credentials.test_address) );
			
			//Basic HTTP Authentication:
			( (Stub) ws )._setProperty( Call.USERNAME_PROPERTY, Credentials.ws_user );
			( (Stub) ws )._setProperty( Call.PASSWORD_PROPERTY, Credentials.pwd_user );
			
			// Cancel or Refund request
			ModificationRequest request = new ModificationRequest();
			request.setMerchantAccount( Credentials.merchant_account );
			request.setOriginalReference( "8513769158577019" );
			
			ModificationResult result = ws.cancelOrRefund( request );
			
			System.out.println( new Date() );
			
			System.out.println( result.getPspReference() );
			System.out.println( result.getResponse() );

		}
		catch ( Exception ex ){
			ex.printStackTrace();
		}

	}

}
