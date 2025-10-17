import Base from "../components/Base";
import { Container, Row, Col } from 'reactstrap';

const About = () => {
    return (

          <Base>

                  <Container className="my-8">
                    <Row>
                      <Col md="12">
                        <div className="border border-gray-300 p-6 rounded-md p-6">
                          <h2 className="text-3xl font-bold mb-4">About Us</h2>
                          <p className="text-gray-600 leading-7">
                            Nagar Nigam (NN) is a local government body committed to providing necessary community services like health care, sanitation, education, housing, roads, transport etc. to the people of the city. NN is very well known for the managing the city by using private sector participation as well as introduction of innovative mechanism in management to serve people efficiently. City has prepared different plans for improving services and to nullify gap between services and demands.
                          </p>
                          <p className="text-gray-600 leading-7 mt-4">
                            Nestled on the banks of the eternal Ganga, Kanpur stands as one of North India's major industrial centers with its own historical, religious and commercial importance. Believed to be founded by king Hindu Singh of the erstwhile state of Sachendi, Kanpur was originally known as 'Kanhapur'. Historically, Jajmau on the eastern outskirts of present day Kanpur is regarded as one of the most archaic townships of Kanpur district.
                          </p>
                          <p className="text-gray-600 leading-7 mt-4">
                            The biggest city of UttarPradesh and eighth biggest in India, Kanpur is the most important metropolis of the state. According to the 2001 census, the city had a population of 25,51,337. Among the big towns of Uttar Pradesh, the growth of Kanpur has been phenomenal. It ranked third after Lucknow and Varanasi in 1901, but by 1961 it assumed a position on top of the list. It has registered an increase of over five times from 1,97,170 in 1901 to 9,71,062 in 1961 in the course of six decades. This is mainly due to its most central location in the state. Kanpur has benefited from its fertile agricultural hinterland of the Upper Ganga Valley and Bundelkhand plateau, the available developed links of transportation and the stimulus of World War-2 with its industrial demand. In this city, in spite of a low percentage of irrigated area, the density is high and that is obviously due to great industrial Concentration.
                          </p>
                        </div>  
                      </Col>
                    </Row>
                  </Container>    


          </Base>

    );
  };
  
  export default About;