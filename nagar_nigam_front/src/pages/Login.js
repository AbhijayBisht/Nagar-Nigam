import { Col, Container, Row } from "reactstrap";
import Base from "../components/Base";
import Header from "../components_login/Header";
import LoginC from "../components_login/LoginC";

const Login = () => {
    return (

        <Base>
                <Container className="outline outline-gray-400 outline-2">
                    <Row className="mt-4">
                        <Col sm={{ size: 6, offset: 3 }}>

                                    
                                            <div>
                                                <h1>this is Login page</h1>
                                                <p>welcome to my Login page</p>
                                            </div>
                                                <Header
                                                    heading="Login to your account"
                                                    paragraph="Don't have an account yet? "
                                                    linkName="Signup"
                                                    linkUrl="/signup"
                                                />
                                                    <LoginC />

                                    

                        </Col>
                    </Row>
                </Container>

        </Base>
    );
  };
  
  export default Login;