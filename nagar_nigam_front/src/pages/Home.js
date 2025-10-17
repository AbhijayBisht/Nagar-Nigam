import React, { useState } from 'react';
import { Container, Row, Col, Form, FormGroup, Label, Input, Button } from 'reactstrap';
import Base from "../components/Base";

const Home = () => {
    
      const [activeSlideId, setActiveSlideId] = useState('ownerDetails');

      const [formData, setFormData] = useState({
        ownerName: '',
        fathersName: '',
        mobileNo: '',
        email: '',
        zone: '',
        corporateWard: '',
        mohalla: '',
        chuckNo: '',
        houseNo: '',
        propertyNo: '',
        wardNo: '',
        houseNo: '',
        nameAndMobile: '',
      });

      const handleInputChange = (e) => {
          setFormData({ ...formData, [e.target.name]: e.target.value });
      };

      const handleSubmit = (e) => {
          e.preventDefault();
          // Handle form submission logic here
          console.log(formData);
      };



      const slides = [
          {
              id: 'ownerDetails',
              title: 'Find details by Owner Details',
              content: (
                  <>
                      <FormGroup>
                          <Label for="ownerName">Enter Owner Name:</Label>
                          <Input
                              type="text"
                              id="ownerName"
                              name="ownerName"
                              value={formData.ownerName}
                              onChange={handleInputChange}
                          />
                      </FormGroup>
                      <FormGroup>
                          <Label for="fathersName">Enter Father's Name:</Label>
                          <Input
                              type="text"
                              id="fathersName"
                              name="fathersName"
                              value={formData.fathersName}
                              onChange={handleInputChange}
                          />
                      </FormGroup>
                      <FormGroup>
                          <Label for="mobileNo">Mobile No:</Label>
                          <Input
                              type="text"
                              id="mobileNo"
                              name="mobileNo"
                              value={formData.mobileNo}
                              onChange={handleInputChange}
                          />
                      </FormGroup>
                      <FormGroup>
                          <Label for="email">Email id:</Label>
                          <Input
                              type="email"
                              id="email"
                              name="email"
                              value={formData.email}
                              onChange={handleInputChange}
                          />
                      </FormGroup>
                  </>
              ),
          },
          {
              id: 'propertyDetails',
              title: 'Find details by Property Details',
              content: (
                  <>
                      <FormGroup>
                          <Label for="zone">Select Zone:</Label>
                          <Input
                              type="select"
                              id="zone"
                              name="zone"
                              value={formData.zone}
                              onChange={handleInputChange}
                          >
                              <option value="">-Zone-</option>
                              <option value="zone1">Zone 1</option>
                              <option value="zone2">Zone 2</option>
                              <option value="zone3">Zone 3</option>
                          </Input>
                      </FormGroup>
                      <FormGroup>
                          <Label for="corporateWard">Select Corporate Ward:</Label>
                          <Input
                              type="select"
                              id="corporateWard"
                              name="corporateWard"
                              value={formData.corporateWard}
                              onChange={handleInputChange}
                          >
                              <option value="">-Corporate Ward-</option>
                              <option value="ward1">Ward 1</option>
                              <option value="ward2">Ward 2</option>
                              <option value="ward3">Ward 3</option>
                          </Input>
                      </FormGroup>
                      <FormGroup>
                          <Label for="mohalla">Select Mohalla:</Label>
                          <Input
                              type="select"
                              id="mohalla"
                              name="mohalla"
                              value={formData.mohalla}
                              onChange={handleInputChange}
                          >
                              <option value="">-Mohalla-</option>
                              <option value="mohalla1">Mohalla 1</option>
                              <option value="mohalla2">Mohalla 2</option>
                              <option value="mohalla3">Mohalla 3</option>
                          </Input>
                      </FormGroup>
                      <FormGroup>
                          <Label for="chuckNo">Select Chuck No:</Label>
                          <Input
                              type="select"
                              id="chuckNo"
                              name="chuckNo"
                              value={formData.chuckNo}
                              onChange={handleInputChange}
                          >
                              <option value="">-Chuck No-</option>
                              <option value="chuck1">Chuck 1</option>
                              <option value="chuck2">Chuck 2</option>
                              <option value="chuck3">Chuck 3</option>
                          </Input>
                      </FormGroup>
                      <FormGroup>
                          <Label for="houseNo">Select House No/Property No:</Label>
                          <Input
                              type="select"
                              id="houseNo"
                              name="houseNo"
                              value={formData.houseNo}
                              onChange={handleInputChange}
                          >
                              <option value="">-House No:Prop No-</option>
                              <option value="house1">House 1</option>
                              <option value="house2">House 2</option>
                              <option value="house3">House 3</option>
                          </Input>
                      </FormGroup>
                  </>
              ),
          },
          {
              id: 'locationDetails',
              title: 'Find details by Location Details',
              content: (
                  <>
                      <FormGroup>
                          <Label for="wardNo">Enter Ward No:</Label>
                          <Input
                              type="text"
                              id="wardNo"
                              name="wardNo"
                              value={formData.wardNo}
                              onChange={handleInputChange}
                          />
                      </FormGroup>
                      <FormGroup>
                          <Label for="houseNo">Enter House No:</Label>
                          <Input
                              type="text"
                              id="houseNo"
                              name="houseNo"
                              value={formData.houseNo}
                              onChange={handleInputChange}
                          />
                      </FormGroup>
                  </>
              ),
          },
          {
              id: 'wardHouseDetails',
              title: 'Find details by Ward & House No',
              content: (
                  <>
                      <FormGroup>
                          <Label for="nameAndMobile">Enter Name/Mobile:</Label>
                          <Input
                              type="text"
                              id="nameAndMobile"
                              name="nameAndMobile"
                              value={formData.nameAndMobile}
                              onChange={handleInputChange}
                          />
                      </FormGroup>
                  </>
              ),
          },
      ];

      
  
  
    return (

          <Base>
              

              <Container className="my-5">
                <Row>
                    <Col md="4" className="border-r border-gray-300 pr-6">
                        <h4 className="text-gray-700 font-bold mb-4">Search by Any Detail</h4>
                        <div className="space-y-2">
                            {slides.map((slide) => (
                                <button
                                    key={slide.id}
                                    className={`block w-full text-left px-4 py-2 hover:bg-gray-200 ${
                                        activeSlideId === slide.id ? 'bg-gray-200' : ''
                                    }`}
                                    onClick={() => setActiveSlideId(slide.id)}
                                >
                                    {slide.title}
                                </button>
                            ))}
                        </div>
                    </Col>
                    <Col md="8">
                        <h4 className="text-gray-700 font-bold mb-4">
                            {slides.find(slide => slide.id === activeSlideId).title}
                        </h4>
                        <Form onSubmit={handleSubmit}>
                            {slides.find(slide => slide.id === activeSlideId).content}
                            <Button type="submit" color="primary" className="w-full mt-4">
                                Submit
                            </Button>
                        </Form>
                    </Col>
                </Row>
              </Container>




          </Base>

    );
  };
  
  export default Home;