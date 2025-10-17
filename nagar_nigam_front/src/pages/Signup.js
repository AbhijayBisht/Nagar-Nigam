import { Col, Container, Row } from "reactstrap";
import Base from "../components/Base";
import Header from "../components_login/Header";
import SignupC from "../components_login/SignupC";

const Signup = () => {
    return (

          <Base>
            <Container>
                <Row>
                    <Col sm={{ size: 6, offset: 3 }}>
                            <div>
                                <h1>this is Sign up page</h1>
                                <p>welcome to my Signup page</p>
                            </div>
                                
                            <Header
                                heading="Signup to create an account"
                                paragraph="Already have an account? "
                                linkName="Login"
                                linkUrl="/login"
                            />
                            <SignupC />
            
                    </Col>
                </Row>
            </Container>

          </Base>

    );
  };
  
  export default Signup;