

import java.util.Date;

import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;

import com.adyen.services.common.Amount;
import com.adyen.services.payment.PaymentLocator;
import com.adyen.services.payment.PaymentPortType;
import com.adyen.services.payment.PaymentRequest;
import com.adyen.services.payment.PaymentResult;
import com.adyen.services.payment.Recurring;
import com.adyen.services.recurring.DisableRequest;
import com.adyen.services.recurring.DisableResult;
import com.adyen.services.recurring.RecurringLocator;
import com.adyen.services.recurring.RecurringPortType;


public class DisablingRecurringDetail {

	public static void main ( String[] args ) {
		try {
			RecurringPortType ws = new RecurringLocator().getRecurringHttpPort( new java.net.URL( Credentials.recurring_test_address) );
			
			//Basic HTTP Authentication:
			( (Stub) ws )._setProperty( Call.USERNAME_PROPERTY, Credentials.ws_user );
			( (Stub) ws )._setProperty( Call.PASSWORD_PROPERTY, Credentials.pwd_user );
			
			DisableRequest request = new DisableRequest();
			request.setMerchantAccount( Credentials.merchant_account );
			request.setRecurringDetailReference( "<recurring detail reference>" );
			request.setShopperReference( "shopperreference" );
			
			DisableResult result =  ws.disable( request );
			System.out.println( new Date() );
			
			System.out.println( result.getResponse() );

		}
		catch ( Exception ex ){
			ex.printStackTrace ();
		}

	}

}
