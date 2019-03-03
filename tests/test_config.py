from configparser import ConfigParser

from hermes import config


def test_write_config(tmpdir):
    d = tmpdir.mkdir('sub').join('config.ini')
    conf = ConfigParser()
    conf.add_section('GENERAL')
    conf.set('GENERAL', 'kek', 'lel')
    config.write_config(d, conf)

    written_config = ConfigParser()
    written_config.read(d)
    assert written_config.has_section('GENERAL')
    assert written_config.has_option('GENERAL', 'kek')


def test_read_config(tmpdir):
    d = tmpdir.mkdir('sub').join('config.ini')
    d.write("""
    [GENERAL]
    kek = lol
    """)

    written_config = config.load_config(d)

    assert written_config.has_section('GENERAL')
    assert written_config.has_option('GENERAL', 'kek')
