import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import InputGroup from 'react-bootstrap/InputGroup';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

export function Footer() {
  return (
    <footer style={{backgroundColor:'#283747', marginTop:'30px'}}>
      <Container>
        <Row>
          <Col md={6}>
            <section>
              <h3 style={{marginTop:'30px', fontWeight:'bold', fontSize:"40px", color:"white"}}>About me</h3>
              <p style={{color:"white"}}>Thank You for visiting my web shop !!!</p>
            </section>
          </Col>
          <Col md={6}>
            <section>
              <h3 style={{marginTop:'30px', fontWeight:'bold', fontSize:"40px", color:"white"}}>Contacts</h3>
              <section style={{marginLeft:'35%'}}>
                <Navbar>
                  <Container>
                  <Navbar.Brand>
                      <a href="https://t.me/Dmytro_Vasylyshyn" target="_blank" rel="noopener noreferrer" style={{color:'white'}}>
                          <i className="bi bi-telegram" ></i> Telegram
                      </a>
                  </Navbar.Brand>
                  </Container>
                </Navbar>
                <Navbar>
                  <Container>
                  <Navbar.Brand>
                      <a href="https://t.me/Dmytro_Vasylyshyn" target="_blank" rel="noopener noreferrer" style={{color:'white'}}>
                          <i className="bi bi-telegram"></i> Instagram
                      </a>
                  </Navbar.Brand>
                  </Container>
                </Navbar>
                <Navbar>
                  <Container>
                      <Navbar.Brand>
                          <a href="www.linkedin.com/in/dmytro-vasylyshyn-165188310" target="_blank" rel="noopener noreferrer" style={{color:'white'}}>
                              <i className="bi bi-linkedin"></i> Linkedin
                          </a>
                      </Navbar.Brand>
                  </Container>
                </Navbar>
                <Navbar >
                  <Container>
                      <Navbar.Brand>
                          <a href="https://github.com/dmytro-vasylyshyn-b" target="_blank" rel="noopener noreferrer" style={{color:'white'}}>
                              <i className="bi bi-github"></i> github
                          </a>
                      </Navbar.Brand>
                  </Container>
                </Navbar>
              </section>
            </section>
          </Col>
        </Row>

        <Row className="mt-4">
          <Col>
            <section style={{display:'flex', alignItems:'center', justifyContent:'center'}}>
              <Navbar className="justify-content-between">
                <Container>
                  <Row className="align-items-center gy-3">
                    <Col xs={12} md={4}>
                      <h2 style={{ color: 'white' }}>Have some feedback?</h2>
                    </Col>

                    <Col xs={12} md={3}>
                      <Form className="d-flex align-items-center">
                        <InputGroup>
                          <Form.Control
                            placeholder="Username"
                            aria-label="Username"
                            aria-describedby="basic-addon1"
                          />
                        </InputGroup>
                      </Form>
                    </Col>

                    <Col xs={12} md={5}>
                      <Form className="d-flex align-items-center">
                        <Row className="w-100">
                          <Col xs={12} sm={8}>
                            <Form.Control
                              type="text"
                              placeholder="Your feedback"
                              style={{ maxWidth: '100%' }}
                            />
                          </Col>
                          <Col xs={12} sm={4} className="d-grid">
                            <Button type="submit" className="w-100 mt-2 mt-sm-0">
                              Submit
                            </Button>
                          </Col>
                        </Row>
                      </Form>
                    </Col>
                  </Row>
                </Container>
              </Navbar>
            </section>
          </Col>
        </Row>
      </Container>
    </footer>
  );
}
