

import java.util.Date;

import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;

import com.adyen.services.common.Amount;
import com.adyen.services.payment.PaymentLocator;
import com.adyen.services.payment.PaymentPortType;
import com.adyen.services.payment.PaymentRequest;
import com.adyen.services.payment.PaymentResult;
import com.adyen.services.payment.Recurring;


public class RecurringPayment {

	public static void main ( String[] args ) {
		try {
			PaymentPortType ws = new PaymentLocator().getPaymentHttpPort( new java.net.URL( Credentials.test_address) );
			
			//Basic HTTP Authentication:
			( (Stub) ws )._setProperty( Call.USERNAME_PROPERTY, Credentials.ws_user );
			( (Stub) ws )._setProperty( Call.PASSWORD_PROPERTY, Credentials.pwd_user );
			
			PaymentRequest request = new PaymentRequest();
			request.setMerchantAccount( Credentials.merchant_account );
			request.setSelectedRecurringDetailReference( "<recurring detail reference>" );
			request.setRecurring( new Recurring( "RECURRING", null) );
			request.setAmount( new Amount( "GBP", 2500) );
			request.setReference( "Recurring Test 1" );
			request.setShopperEmail("shoppedemail@server.com" );
			request.setShopperReference( "shopperreference" );
			request.setShopperInteraction( "ContAuth" );
			
			PaymentResult result = ws.authorise( request );
			
			System.out.println( new Date() );
			
			System.out.println( result.getPspReference() );
			System.out.println( result.getResultCode() );
			System.out.println( result.getAuthCode() );
			System.out.println( result.getRefusalReason() );

		}
		catch ( Exception ex ){
			ex.printStackTrace();
		}

	}

}
