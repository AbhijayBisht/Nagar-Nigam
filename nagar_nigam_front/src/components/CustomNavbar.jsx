import React, { useState } from 'react';
import {
  Navbar,
  NavbarBrand,
  NavbarToggler,
  Collapse,
  Nav,
  NavItem,
  NavLink,
  Container
} from 'reactstrap';
import logoImg from '../images/logo_img.jpg';
import { NavLink as ReactLink } from 'react-router-dom';


const CustomNavbar = () => {
    
            const [isOpen, setIsOpen] = useState(false);

            const toggle = () => setIsOpen(!isOpen);
    
    return (



        <div>
            <Navbar expand="md" className="bg-white" light>
                <Container className="flex items-center justify-between border-b border-gray-300">
                    <NavbarBrand href="/" className="flex items-center text-black">
                        
                            <img src={logoImg}
                                 alt="Nagar Nigam Logo"
                                 className="h-20 w-20 mr-2" />

                            <div >
                                <div className="text-lg font-bold">NAGAR NIGAM</div>
                                <div className="text-sm">नगर निगम</div>
                            </div>
                            
                    </NavbarBrand>
                    
                    <NavbarToggler onClick={toggle} className="border-black">
                        <span className="navbar-toggler-icon"></span>
                    </NavbarToggler>

                    <Collapse isOpen={isOpen} navbar>
                        <Nav className="ms-auto" navbar>
                            <NavItem>
                                <NavLink tag={ReactLink} to="/" className="text-black hover:text-gray-700">
                                    Home
                                </NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink tag={ReactLink} to="/about" className="text-black hover:text-gray-700">
                                    About us
                                </NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink tag={ReactLink} to="/contact" className="text-black hover:text-gray-700">
                                    Contact
                                </NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink tag={ReactLink} to="#" className="text-black hover:text-gray-700">
                                    Admin
                                </NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink tag={ReactLink} to="/login" className="text-black hover:text-gray-700">
                                    Login
                                </NavLink>
                            </NavItem>
                        </Nav>
                    </Collapse>


                </Container>
            </Navbar>



            <div className="text-black p-2">
            <Container>
                <h2 className="text-xl m-0">Property Tax Management System</h2>
            </Container>
            </div>

      </div>
    );
  };
  
  export default CustomNavbar;