import Base from "../components/Base";
import { Container, Row, Col } from 'reactstrap';

const Contact = () => {
    return (

          <Base>
            
                <Container className="my-8">
                  <Row className="grid grid-cols-2 gap-8">
                    <Col>
                      <div className="h-full flex flex-col justify-center border border-gray-300 p-6 rounded-md">
                          <h2 className="text-3xl font-bold mb-4">Get in touch</h2>
                          <p className="text-gray-600 leading-7">
                            For any concern regarding your Annual Tax, Your Bill, please contact your Zone Office. For any concern regarding your payments please email to taxconcernknn@gmail.com with payment details.
                          </p>
                          <div className="mt-6">
                            <h3 className="text-xl font-bold">Kanpur Nagar Nigam</h3>
                            <p className="text-gray-600">
                              Nagar Nigam Mukhyalay,<br />
                              Moti Jheel, Harsh Nagar,<br />
                              Kanpur, Uttar Pradesh 208002.
                            </p>
                          </div>
                          <div className="mt-4">
                            <h3 className="text-xl font-bold">Email</h3>
                            <p className="text-gray-600">taxconcernnn@gmail.com</p>
                          </div>
                      </div>
                    </Col>
                    <Col>
                      <iframe
                        title="Kanpur Municipal Corporation"
                        src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d14305.192667037428!2d80.3277185!3d26.4588119!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x37d4aba5c9d04765!2sKanpur%20Municipal%20Corporation!5e0!3m2!1sen!2sus!4v1624363847261!5m2!1sen!2sus"
                        width="100%"
                        height="400"
                        style={{ border: 0, borderRadius: "0.5rem" }}
                        allowFullScreen={false}
                        loading="lazy"
                      ></iframe>
                    </Col>
                  </Row>
                </Container>


          </Base>

    );
  };
  
  export default Contact;